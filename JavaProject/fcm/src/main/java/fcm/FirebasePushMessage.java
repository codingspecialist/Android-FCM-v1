package fcm;

import java.io.FileInputStream;

import org.slf4j.Logger;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

public class FirebasePushMessage {

	
	void init() {
		FileInputStream serviceAccount = null;
		try {
			serviceAccount = new FileInputStream("C:/src/secrect/fcmtest-fde9e-firebase-adminsdk-dj0fm-e5cb456822.json");
			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount))
					.setDatabaseUrl("https://fcmtest-fde9e.firebaseio.com").build();

			FirebaseApp.initializeApp(options);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	void sendMessage() {
		// The topic name can be optionally prefixed with "/topics/".
		String topic = "HighScores";

		// See documentation on defining a message payload.
		Message message = Message.builder()
		    .putData("score", "1000")
		    .putData("time", "2:45")
		    .setTopic(topic)
		    .build();

		// Send a message to the devices subscribed to the provided topic.
		String response = null;
		try {
			response = FirebaseMessaging.getInstance().send(message);
		} catch (FirebaseMessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Response is a message ID string.
		
		System.out.println("Successfully sent message: " + response);
	}

	public static void main(String[] args) {
		FirebasePushMessage fpm = new FirebasePushMessage();
		fpm.init();
		fpm.sendMessage();
	}
}
