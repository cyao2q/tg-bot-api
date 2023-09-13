package se.anyro.tgbotapi.types;

import se.anyro.tgbotapi.types.inline.CallbackQuery;
import se.anyro.tgbotapi.types.inline.ChosenInlineResult;
import se.anyro.tgbotapi.types.inline.InlineQuery;
import se.anyro.tgbotapi.types.payments.PreCheckoutQuery;
import se.anyro.tgbotapi.types.payments.ShippingQuery;
import se.anyro.tgbotapi.types.poll.Poll;
import se.anyro.tgbotapi.types.poll.PollAnswer;

/**
 * @see <a href="https://core.telegram.org/bots/api#update">Update</a>
 */
public class Update {
    public int update_id;
    public Message message;
    public Message edited_message;
    public Message channel_post;
    public Message edited_channel_post;
    public InlineQuery inline_query;
    public ChosenInlineResult chosen_inline_result;
    public CallbackQuery callback_query;
    public ShippingQuery shipping_query;
    public PreCheckoutQuery pre_checkout_query;
    public Poll poll;
    public PollAnswer poll_answer;

    public enum Type {
        MESSAGE, EDITED_MESSAGE, CHANNEL_POST, EDITED_CHANNEL_POST, INLINE_QUERY, CHOSEN_INLINE_RESULT, CALLBACK_QUERY, SHIPPING_QUERY, PRE_CHECKOUT_QUERY, POLL, POLL_ANSWER, UNKNOWN
    }

    public boolean isMessage() {
        return message != null;
    }

    public boolean isEditedMessage() {
        return edited_message != null;
    }

    public boolean isChannelPost() {
        return channel_post != null;
    }
    
    public boolean isEditedChannelPost() {
        return edited_channel_post != null;
    }
    
    public boolean isInlineQuery() {
        return inline_query != null;
    }

    public boolean isChosenInlineResult() {
        return chosen_inline_result != null;
    }

    public boolean isCallbackQuery() {
        return callback_query != null;
    }

    public boolean isShippingQuery() {
        return shipping_query != null;
    }

    public boolean isPreCheckoutQuery() {
        return pre_checkout_query != null;
    }

    public boolean isPoll() {
        return poll != null;
    }

    public boolean isPollAnswer() {
        return poll_answer != null;
    }

    public Type getType() {
        if (isMessage()) {
            return Type.MESSAGE;
        } else if (isEditedMessage()) {
            return Type.EDITED_MESSAGE;
        } else if (isChannelPost()) {
            return Type.CHANNEL_POST;
        } else if (isEditedChannelPost()) {
            return Type.EDITED_CHANNEL_POST;
        } else if (isInlineQuery()) {
            return Type.INLINE_QUERY;
        } else if (isChosenInlineResult()) {
            return Type.CHOSEN_INLINE_RESULT;
        } else if (isCallbackQuery()) {
            return Type.CALLBACK_QUERY;
        } else if (isShippingQuery()) {
            return Type.SHIPPING_QUERY;
        } else if (isPreCheckoutQuery()) {
            return Type.PRE_CHECKOUT_QUERY;
        } else if (isPoll()) {
            return Type.POLL;
        } else if (isPollAnswer()) {
            return Type.POLL_ANSWER;
        }
        return Type.UNKNOWN;
    }

    /**
     * Returns the user who sent the message. Might return null for messages in channels or new types of updates.
     */
    public User fromUser() {
        Type type = getType();
        if (type == null) {
            return null;
        }
        switch (type) {
        case MESSAGE:
            return message.from;
        case EDITED_MESSAGE:
            return edited_message.from;
        case CHANNEL_POST:
            return channel_post.from;
        case EDITED_CHANNEL_POST:
            return edited_channel_post.from;
        case INLINE_QUERY:
            return inline_query.from;
        case CHOSEN_INLINE_RESULT:
            return chosen_inline_result.from;
        case CALLBACK_QUERY:
            return callback_query.from;
        case SHIPPING_QUERY:
            return shipping_query.from;
        case PRE_CHECKOUT_QUERY:
            return pre_checkout_query.from;
        case POLL:
            return null;
        case POLL_ANSWER:
            return poll_answer.user;
        default:
            return null;
        }
    }

    /**
     * Returns the id of the user who sent the message. Might return 0 for messages in channels or new types of updates.
     */
    public long fromUserId() {
        User from = fromUser();
        if (from != null) {
            return from.id;
        }
        return 0;
    }

    public Chat chat() {
        Type type = getType();
        if (type == null) {
            return null;
        }
        switch (type) {
        case MESSAGE:
            return message.chat;
        case EDITED_MESSAGE:
            return edited_message.chat;
        case CHANNEL_POST:
            return channel_post.chat;
        case EDITED_CHANNEL_POST:
            return edited_channel_post.chat;
        case CALLBACK_QUERY:
            if (callback_query.message != null) {
                return callback_query.message.chat;
            }
        case INLINE_QUERY:
        case CHOSEN_INLINE_RESULT:
        case SHIPPING_QUERY:
        case PRE_CHECKOUT_QUERY:
        default:
            return null;
        }
    }
}