package sara.openclassrooms.moodtracker;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CANAL = "MyNotifCanal";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //when i receive a message from Firebase is open it
        super.onMessageReceived(remoteMessage);

        //je recupere l'element dans remoteMessage et le corps le contenu
        String myMessage = remoteMessage.getNotification().getBody();
        //permet d'affficher le message dans la console
        Log.d("FirebaseMessage", "Vous venez de recevoir une notification :" + myMessage);


        //creer une notification
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CANAL);
        notificationBuilder.setContentTitle("MoodTracker");
        notificationBuilder.setContentText(myMessage);

        //icone
        notificationBuilder.setSmallIcon(R.drawable.bell);

        //envoyer la notif
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, notificationBuilder.build());
    }
}
