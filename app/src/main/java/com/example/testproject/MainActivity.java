package com.example.testproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    //파이어베이스에 데이터를 추가나 조회하기 위해 사용

    Button save, read;
    EditText email, name, age, id;
    TextView data;
    int i = 1; //primary Key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        //데이터를 읽거나 쓰기위해서는 DatabaseReference의 인스턴스가 필요
        save = findViewById(R.id.buttonSave); //저장 버튼
        read = findViewById(R.id.buttonRead); //가져오기 버튼
        name = findViewById(R.id.editName); //이름
        email = findViewById(R.id.editEmail); // 이메일
        age = findViewById(R.id.editAge); // 나이
        id = findViewById(R.id.editID);// id
        data = findViewById(R.id.textData); // 가져올 데이터

        save.setOnClickListener(new View.OnClickListener() {// 저장 버튼누르면 값저장
            @Override
            public void onClick(View v) {
                String getUserName = name.getText().toString();
                String getUserEmail = email.getText().toString();
                String getUserAge = age.getText().toString();
                writeUser(Integer.toString(i++), getUserName, getUserEmail, getUserAge);//함수
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readUser(id.getText().toString());

            }
        });
    }

        private void writeUser(String userId, String name, String email, String age){ //함수
            User user = new User(name, email, age);
            //데이터 저장
            mDatabase.child("users").child(userId).setValue(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() { //데이터베이스에 넘어간 이후 처리
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"저장을 완료했습니다", Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"저장에 실패했습니다" , Toast.LENGTH_LONG).show();
                        }
                    });
    }
    private void readUser(String userId) {//함수
        //데이터 읽기
        mDatabase.child("users").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);

                data.setText("이름: " + user.name + "\n이메일: " + user.email + "\n나이: " + user.age);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}