package ServerManagement;

import Model.AdminEntity;
import Model.AdminOperations;
import Model.JurnalistEntity;
import Model.JurnalistOperations;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientHandler extends Thread
{
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;


    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos)
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
    }

    public void sendCommand(String command)
    {
        try {
            dos.writeUTF(command);
            // printing date or time as requested by client


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        String received;
        String toreturn;
        AdminEntity adminEntity;
        JurnalistEntity jurnalistEntity;
        AdminOperations adminOperations=new AdminOperations();
        JurnalistOperations jurnalistOperations= new JurnalistOperations();
        Gson gson = new Gson();
        while (true)
        {
            try {

                // Ask user what he wants


                // receive the answer from client
                received = dis.readUTF();

                String[] vectReceived=received.split("\n");
                received=vectReceived[0];
                //System.out.println(received+" "+vectReceived[1]);
                if(received.equals("Exit"))
                {
                    dos.writeUTF("Exit");
                    System.out.println("Client " + this.s + " sends exit...");
                    System.out.println("Closing this connection.");
                    this.s.close();
                    System.out.println("Connection closed");
                    break;
                }
                //System.out.println(received);
                // creating Date object
                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "Date" :
                        toreturn = fordate.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    case "Time" :
                        toreturn = fortime.format(date);
                        dos.writeUTF(toreturn);
                        break;
                    case "AdminInsert" :
                        //JurnalistEntity jurnalistEntity=gson.fromJson(vectReceived[1],JurnalistEntity.class);
                        adminEntity=gson.fromJson(vectReceived[1],AdminEntity.class);
                        adminOperations.insert(adminEntity);
                        System.out.println(adminEntity.getId());
                        dos.writeUTF("Succes inserare");
                        break;
                    case "Logare admin":
                        adminEntity=gson.fromJson(vectReceived[1],AdminEntity.class);
                        adminOperations.logare(adminEntity,this);
                        System.out.println(adminEntity.getUsername());
                        dos.writeUTF("Succes Logare!");
                        break;
                    case "Delogare":
                        adminEntity=gson.fromJson(vectReceived[1],AdminEntity.class);
                        adminOperations.delogare(adminEntity);
                        break;
                    case "adaugaJurnalist":
                        jurnalistEntity=gson.fromJson(vectReceived[1],JurnalistEntity.class);
                        adminOperations.adaugaJurnalist(jurnalistEntity,this);
                        break;
                    case "DelogareJurnalist":
                        jurnalistEntity=gson.fromJson(vectReceived[1],JurnalistEntity.class);
                        jurnalistOperations.delogare(jurnalistEntity);
                        break;
                    case "Logare jurnalist":
                        jurnalistEntity=gson.fromJson(vectReceived[1],JurnalistEntity.class);
                        jurnalistOperations.logare(jurnalistEntity,this);
                        //System.out.println(adminEntity.getUsername());
                        break;
                    default:
                        dos.writeUTF("Invalid input!");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try
        {
            // closing resources
            this.dis.close();
            this.dos.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}

