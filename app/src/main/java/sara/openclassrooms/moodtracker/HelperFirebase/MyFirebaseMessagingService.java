package sara.openclassrooms.moodtracker.HelperFirebase;

import android.app.NotificationManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import sara.openclassrooms.moodtracker.R;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CANAL = "MyNotifCanal";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //when i receive a message from Firebase is open it
        super.onMessageReceived(remoteMessage);


        String myMessage = remoteMessage.getNotification().getBody();

        Log.d("FirebaseMessage", "Vous venez de recevoir une notification :" + myMessage);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, CANAL);
        notificationBuilder.setContentTitle("MoodTracker");
        notificationBuilder.setContentText(myMessage);

        notificationBuilder.setSmallIcon(R.drawable.bell);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1, notificationBuilder.build());
    }
}
