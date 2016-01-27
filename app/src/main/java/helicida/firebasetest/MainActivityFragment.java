package helicida.firebasetest;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Firebase ref;

    public MainActivityFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        configFirebase();

        ref = new Firebase("https://helicidatest.firebaseio.com/");

        ref.child("message").setValue("Mensaje prueba");

        // Generamos dos objetos con la clave de referencia  a mano
        Firebase sergiRef = ref.child("users").child("sergi");
        User sergi = new User("Sergi", 1996);
        sergiRef.setValue(sergi);

        Firebase mireiaRef = ref.child("users").child("mireia");
        User mireia = new User("Mireia", 1990);
        mireiaRef.setValue(mireia);

        // Usando push automaitcamente genera la clave de referencia
        /*
        Firebase users = ref.child("users");
        Firebase usersRef = users.push();
        usersRef.setValue(new User("Jordi", 2001));*/

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
        });

        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    public void configFirebase(){
        Firebase.setAndroidContext(getContext());
    }
}
