package mx.linkom.caseta_modulo_wifi2;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "TOKEN";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        mx.linkom.caseta_modulo_wifi2.Global.TOKEN = FirebaseInstanceId.getInstance().getToken();
    }

}