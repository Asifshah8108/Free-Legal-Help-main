package com.myapplicationname.legalhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import javax.mail.Session;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FillForm extends AppCompatActivity {
    private EditText emailId,mobileNo,name,describe;
    private Button submit;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    AutoCompleteTextView autoCompleteTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_form);

        emailId = findViewById(R.id.emailId);
        mobileNo = findViewById(R.id.mobileNo);
        name = findViewById(R.id.name);
        describe = findViewById(R.id.describe);
        submit = findViewById(R.id.submit);

        autoCompleteTextView = findViewById(R.id.filled_exposed);

        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("LawFormInfo");




        String [] Agreements=new String[]{"Agreements","Arbitration","Civil","Criminal","Family matters","Legal Notices","Consumer Courts","Co-operative Courts","Labour issues","Taxation"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (FillForm.this,
                        R.layout.dropdown,
                        Agreements);
        AutoCompleteTextView autoCompleteTextView = findViewById(R.id.filled_exposed);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(FillForm.this, autoCompleteTextView.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDataToFirebase();
            }
        });
    }

    public void addDataToFirebase(){
        String txt_name = name.getText().toString();
        String txt_email = emailId.getText().toString();
        String txt_mobile = mobileNo.getText().toString();
        String txt_select = autoCompleteTextView.getText().toString();
        String txt_describe = describe.getText().toString();

        LawFormInfo lawFormInfo = new LawFormInfo(txt_name,txt_email,txt_mobile,txt_select,txt_describe);

        databaseReference.child(txt_name).setValue(lawFormInfo);
        name.setText("");
        emailId.setText("");
        mobileNo.setText("");
        autoCompleteTextView.setText("");
        describe.setText("");


        if (TextUtils.isEmpty(txt_name) && TextUtils.isEmpty(txt_email) && TextUtils.isEmpty(txt_mobile) && TextUtils.isEmpty(txt_select) && TextUtils.isEmpty(txt_describe)){
            Toast.makeText(FillForm.this, "Please fill all the fields First", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Your Query has been Submitted you will be replyed on your Entered Email soon", Toast.LENGTH_SHORT).show();


            try {
                String stringSenderEmail = "freelegalhelpsend@gmail.com";
                String stringReceiverEmail = "Freelegalhelp1111@gmail.com";
//                --> Password:- narayan2010
                String stringPasswordSenderEmail = "lqkvjcknovjyickg";

                String stringHost = "smtp.gmail.com";

                Properties properties = System.getProperties();

                properties.put("mail.smtp.host", stringHost);
                properties.put("mail.smtp.port", "465");
                properties.put("mail.smtp.ssl.enable", "true");
                properties.put("mail.smtp.auth", "true");

                javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
                    }
                });

                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));

                mimeMessage.setSubject("Subject: Free Legal Help");
                mimeMessage.setText("name :" + txt_name + "\n" + "Email :" + txt_email + "\n" + "Mobile :" + txt_mobile + "\n" + "Help required :" + txt_select + "\n" + "Described information :" + txt_describe);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Transport.send(mimeMessage);
                        } catch (MessagingException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

            } catch (AddressException e) {
                e.printStackTrace();
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
//    public void sendToMail(){
//
//        String mail_name = name.getText().toString();
//        String mail_email = emailId.getText().toString();
//        String mail_mobile = mobileNo.getText().toString();
//        String mail_select = autoCompleteTextView.getText().toString();
//        String mail_describe = describe.getText().toString();
//
//
//        if (TextUtils.isEmpty(mail_name) && TextUtils.isEmpty(mail_email) && TextUtils.isEmpty(mail_mobile) && TextUtils.isEmpty(mail_select) && TextUtils.isEmpty(mail_describe)){
//            Toast.makeText(FillForm.this, "Please fill all the fields First", Toast.LENGTH_SHORT).show();
//        }else {
//            Toast.makeText(FillForm.this, "Your Query has been Submitted you will be replyed on your Entered Email soon", Toast.LENGTH_SHORT).show();
//        }
//
//        try {
//            String stringSenderEmail = "ankushrsingh2002@gmail.com";
//            String stringReceiverEmail = "asifshah7021063407@gmail.com";
//            String stringPasswordSenderEmail = "hgahspwgoynabosc";
//
//            String stringHost = "smtp.gmail.com";
//
//            Properties properties = System.getProperties();
//
//            properties.put("mail.smtp.host", stringHost);
//            properties.put("mail.smtp.port", "465");
//            properties.put("mail.smtp.ssl.enable", "true");
//            properties.put("mail.smtp.auth", "true");
//
//            javax.mail.Session session = Session.getInstance(properties, new Authenticator() {
//                @Override
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(stringSenderEmail, stringPasswordSenderEmail);
//                }
//            });
//
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(stringReceiverEmail));
//
//            mimeMessage.setSubject("Subject: Free Legal Help");
//            mimeMessage.setText("name :"+mail_name+"\n"+"Email :"+mail_email+"\n"+"Mobile :"+mail_mobile+"\n"+"Help required :"+mail_select+"\n"+"Described information :"+mail_describe);
//
//            Thread thread = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Transport.send(mimeMessage);
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//            thread.start();
//
//        } catch (AddressException e) {
//            e.printStackTrace();
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//
//
//    }
}
