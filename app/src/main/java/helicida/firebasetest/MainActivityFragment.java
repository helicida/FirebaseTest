package helicida.firebasetest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    Firebase ref;
    Firebase users;
    TextView text1, text2;

    public MainActivityFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View fragmentoLista = inflater.inflate(R.layout.fragment_main, container, false); //Definimos el fragment

        configFirebase();

        ref = new Firebase("https://helicidatest.firebaseio.com/");

        users = ref.child("users");

        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("XXXX", dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        /*

        ref.child("message").setValue("Mensaje prueba");

        // Generamos dos objetos con la clave de referencia  a mano
        Firebase sergiRef = ref.child("users").child("sergi");
        User sergi = new User("Sergi", 1996);
        sergiRef.setValue(sergi);

        Firebase mireiaRef = ref.child("users").child("mireia");
        User mireia = new User("Mireia", 1990);
        mireiaRef.setValue(mireia);

        // Usando push automaitcamente genera la clave de referencia

        Firebase users = ref.child("users");
        Firebase usersRef = users.push();
        usersRef.setValue(new User("Jordi", 2001));

        // Ejemplo de como leer información de la BBDD
        ref.child("users").child("sergi").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView text = (TextView) getView().findViewById(R.id.texto);
                text.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });*/

        ListView listUsers = (ListView) fragmentoLista.findViewById(R.id.listView1);

        FirebaseListAdapter mAdapter = new FirebaseListAdapter<User>(getActivity(), User.class, R.layout.adapter_firebase_users_layout, users) {
                @Override
                protected void populateView(View view, User user, int position) {
                    super.populateView(view, user, position);
                    text1 = (TextView) view.findViewById(R.id.text1);
                    text1.setText(user.getFullName());

                    text2 = (TextView) view.findViewById(R.id.text2);
                    text2.setText(String.valueOf(user.getBirthYear()));
                }
        };

        listUsers.setAdapter(mAdapter);

        return fragmentoLista;
    }

    public void configFirebase(){
        Firebase.setAndroidContext(getContext());
    }
}
