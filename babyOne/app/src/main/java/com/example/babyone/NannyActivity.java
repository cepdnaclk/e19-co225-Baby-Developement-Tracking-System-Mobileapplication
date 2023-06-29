package com.example.babyone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NannyActivity extends AppCompatActivity {

    RecyclerView recViewNanny;
    EditText edtNannyMsg;
    TextView btnNannySend;
    List<ChatMessage> messageList;

    ChatMessageAdapter messageAdapter;

    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanny);

        recViewNanny = findViewById(R.id.recViewNanny);
        edtNannyMsg = findViewById(R.id.edtNannyMsg);
        btnNannySend = findViewById(R.id.btnNannySend);
        messageList = new ArrayList<>();

        // rec view setup
        messageAdapter = new ChatMessageAdapter(messageList);
        recViewNanny.setAdapter(messageAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setStackFromEnd(true);
        recViewNanny.setLayoutManager(llm);

        btnNannySend.setOnClickListener((v -> {
            String question = edtNannyMsg.getText().toString().trim();
            addToChat(question,ChatMessage.SENT_BY_ME);
            edtNannyMsg.setText("");
            callAPI(question);
        }));

    }

    void addToChat(String message, String sentBy){
        messageList.add(new ChatMessage(message,sentBy));
        messageAdapter.notifyDataSetChanged();
        recViewNanny.smoothScrollToPosition(messageAdapter.getItemCount());
    }

    void addResponse(String response){
        runOnUiThread(() -> {
            messageList.remove(messageList.size()-1);
            addToChat(response,ChatMessage.SENT_BY_BOT);
        });
    }

    void callAPI(String question){

        //typing anim
        messageList.add(new ChatMessage("typing-anim...",ChatMessage.SENT_BY_BOT));

        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("model", "text-davinci-003");
            jsonBody.put("prompt", question);
            jsonBody.put("max_tokens", 200);
            jsonBody.put("temperature", 0);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        RequestBody body = RequestBody.create(jsonBody.toString(),JSON);
        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/completions")
                .header("Authorization","Bearer " + getString(R.string.openai_api_key))
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                addResponse("Failed to load response: \n"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    try {
                        assert response.body() != null;
                        JSONObject jsonObject = new JSONObject(response.body().string());
                        JSONArray jsonArray= jsonObject.getJSONArray("choices");
                        String result = jsonArray.getJSONObject(0).getString("text");
                        addResponse(result.trim());
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }else{
                    assert response.body() != null;
                    addResponse("Failed to load response: \n"+response.body().toString());
                }
            }
        });
    }

}