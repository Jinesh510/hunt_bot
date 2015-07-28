package jinesh.urbanhunt_test;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;

import com.parse.ParsePushBroadcastReceiver;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jinesh on 24/07/15.
 */
public class HuntPushBroadcastReceiver extends ParsePushBroadcastReceiver {

    @Override
    protected void onPushReceive(Context context, Intent intent) {
        super.onPushReceive(context, intent);

        String id = null;
        JSONObject data;
        try {
             data = new JSONObject(intent.getExtras().getString("com.parse.Data"));
            id = data.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        switch (id){
            case "1":
                m1(context);
                break;
        }

    }

    public void m1(Context context) {
        Intent resultIntent = new Intent(context, FilterCategories.class);
        resultIntent.putExtra("category","bags");
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(FilterCategories.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("Look what we got for you");
        builder.setContentText("Tap reserve to book your hunt");
        builder.setSmallIcon(R.drawable.ic_push);
        Bitmap b = BitmapFactory.decodeResource(context.getResources(), R.drawable.comp_logo);
        Bitmap b1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.push_image);

        builder.setLargeIcon(b);
        builder.setAutoCancel(true);
        builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(b1));
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify("MyTag", 0, builder.build());

    }

    @Override
    protected void onPushOpen(Context context, Intent intent) {
        super.onPushOpen(context, intent);
    }

    @Override
    protected Class<? extends Activity> getActivity(Context context, Intent intent) {
        return super.getActivity(context, intent);
    }

    @Override
    protected Notification getNotification(Context context, Intent intent) {
//        return super.getNotification(context, intent);
        return null;
    }

    @Override
    protected void onPushDismiss(Context context, Intent intent) {
        super.onPushDismiss(context, intent);
    }


}
