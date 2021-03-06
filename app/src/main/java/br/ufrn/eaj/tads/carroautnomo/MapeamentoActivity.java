package br.ufrn.eaj.tads.carroautnomo;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import br.ufrn.eaj.tads.carroautnomo.Modelo.Mapeamento;

public class MapeamentoActivity extends AppCompatActivity {

    private FirebaseDatabase mFirebase;
    private DatabaseReference mReference;

    private ChildEventListener mChildEventListener;

    private ImageView tabuleiro[][];
    private GridLayout grid;
    private LayoutInflater inflate;

    private Button voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapeamento);

        mFirebase = FirebaseDatabase.getInstance();
        mReference = mFirebase.getReference().child("mapeamento");

        grid = findViewById(R.id.gridBoardM);
        grid.setRowCount(31);
        grid.setColumnCount(19);

        inflate = LayoutInflater.from(this);

        tabuleiro = new ImageView[grid.getRowCount()][grid.getColumnCount()];

        for(int i = 0; i < grid.getRowCount(); i++){
            for(int j = 0; j < grid.getColumnCount(); j++){
                tabuleiro[i][j] = (ImageView) inflate.inflate(R.layout.layout_inflate_imagewhite, grid,false);
                grid.addView(tabuleiro[i][j]);
            }
        }

        //tabuleiro[16][10].setImageResource(R.drawable.blocograyp);

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Mapeamento m = dataSnapshot.getValue(Mapeamento.class);
                tabuleiro[m.getLinha()][m.getColuna()].setImageResource(R.drawable.blocograyp);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        mReference.addChildEventListener(mChildEventListener);

    }

    public void voltar(View v){
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /*mReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mReference.child("mapeamento").removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/
    }

}
