package com.applozic.mobicomkit.api.conversation;

import android.app.IntentService;
import android.content.Intent;

import com.applozic.mobicomkit.api.account.user.UserService;
import com.applozic.mobicommons.people.channel.Channel;
import com.applozic.mobicommons.people.contact.Contact;

/**
 * Created by sunil on 23/7/16.
 */
public class ConversationReadService extends IntentService {

    private static final String TAG = "ConversationReadService";
    public static final String CONTACT = "contact";
    public static final String CHANNEL = "channel";
    public static final String UNREAD_COUNT = "UNREAD_COUNT";
    public static final String SINGLE_MESSAGE_READ = "SINGLE_MESSAGE_READ";

    public ConversationReadService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent == null){
            return;
        }
        MessageClientService messageClientService =   new MessageClientService(getApplicationContext());
        Integer unreadCount = intent.getIntExtra(UNREAD_COUNT, 0);
        boolean singleMessageRead = intent.getBooleanExtra(SINGLE_MESSAGE_READ,false);
        Contact contact = (Contact) intent.getSerializableExtra(CONTACT);
        Channel channel = (Channel) intent.getSerializableExtra(CHANNEL);
        if (unreadCount != 0 || singleMessageRead) {
            messageClientService.updateReadStatus(contact, channel);
        }
    }
}
