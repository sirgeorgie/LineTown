package com.company.linetownelection.WebSocket;

import com.company.linetownelection.entity.Message;
import com.company.linetownelection.entity.OutputMessage;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageHandling {

    @MessageMapping("/liveVote")
    @SendTo("/topic/messages")
    public OutputMessage send(Message message) throws Exception {
        String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new OutputMessage(message.getId(), message.getVotedCount(), time);
    }

//    runOnUiThread(new Runnable() {
//        @Override
//        public void run() {
//            try {
//                JSONObject object = new JSONObject(text);
//                StringBuilder builder = new StringBuilder();
//                if (object.getBoolean("status")) {
//                    JSONArray jsonArray = object.getJSONArray("events");
//                    for (int i = 0; i < jsonArray.length(); i++) {
//                        JSONObject values = jsonArray.getJSONObject(i);
//                        final EventsDataModel dataModel = new EventsDataModel(
//                                values.getString("service_Room_Number"),
//                                values.getString("service_Name"),
//                                values.getString("service_AssignedTo"),
//                                values.getString("service_ID")
//                        );
//                        eventsDataModels.add(dataModel);
//                        adapter = new EventListAdapter(eventsDataModels, context);
//                        eventRecyclerView.setAdapter(adapter);
//                        adapter.notifyDataSetChanged();
//                        how exactly notifyDataSetChanged() works?
//
//                    }
//                } else Toast.makeText(context, "No Events", Toast.LENGTH_SHORT).show();
//                System.out.println(builder.append(object.getString("status")));
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//        }
//    });

}
