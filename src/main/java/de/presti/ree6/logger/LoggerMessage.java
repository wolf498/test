package de.presti.ree6.logger;

import club.minnced.discord.webhook.send.WebhookMessage;
import net.dv8tion.jda.api.entities.Guild;

/**
 * This class is used for merging LoggingMessage to save Webhook Messages
 * to prevent Rate-Limits.
 */
public class LoggerMessage {

    // Webhook ID, used to tell Discord which Webhook should be used.
    private long id;

    // Webhook AuthCode, used to authenticate.
    private String authCode;

    //  Webhook State, this is used to cancel Webhooks that have been merged with others.
    private boolean cancel = false;

    // The WebhookMessage.
    private WebhookMessage webhookMessage;

    // Webhook Guild, the Guild which fired the Log.
    private Guild guild;

    // RoleData from Webhook Logs.
    private LoggerRoleData roleData;

    // VoiceData from Webhook Logs.
    private LoggerVoiceData voiceData;

    // MemberData from Webhook Logs.
    private LoggerMemberData memberData;

    // LoggerTyp, to know what kind of Log this Message is.
    private LogTyp type;

    /**
     * Constructor for a Log-Message which shouldn't be handled.
     *
     * @param g              Guild of the Log-Message.
     * @param c              Webhook ID.
     * @param auth           Webhook Auth-Code.
     * @param webhookMessage WebhookMessage itself.
     * @param type           LogTyp.
     */
    public LoggerMessage(Guild g, long c, String auth, WebhookMessage webhookMessage, LogTyp type) {
        this.guild = g;
        this.id = c;
        this.authCode = auth;
        this.webhookMessage = webhookMessage;
        this.type = type;
    }

    /**
     * Constructor for a Log-Message which should be used to handle UserData.
     *
     * @param g              Guild of the Log-Message.
     * @param c              Webhook ID.
     * @param auth           Webhook Auth-Code.
     * @param webhookMessage WebhookMessage itself.
     * @param memberData     Provided UserData used for the Log.
     * @param type           LogTyp.
     */
    public LoggerMessage(Guild g, long c, String auth, WebhookMessage webhookMessage, LoggerMemberData memberData, LogTyp type) {
        this.guild = g;
        this.id = c;
        this.authCode = auth;
        this.webhookMessage = webhookMessage;
        this.memberData = memberData;
        this.type = type;
    }

    /**
     * Constructor for a Log-Message which should be used to handle RoleData.
     *
     * @param g              Guild of the Log-Message.
     * @param c              Webhook ID.
     * @param auth           Webhook Auth-Code.
     * @param webhookMessage WebhookMessage itself.
     * @param roleData       Provided RoleData used for the Log.
     * @param type           LogTyp.
     */
    public LoggerMessage(Guild g, long c, String auth, WebhookMessage webhookMessage, LoggerRoleData roleData, LogTyp type) {
        this.guild = g;
        this.id = c;
        this.authCode = auth;
        this.webhookMessage = webhookMessage;
        this.roleData = roleData;
        this.type = type;
    }

    /**
     * Constructor for a Log-Message which should be used to handle RoleData.
     *
     * @param g              Guild of the Log-Message.
     * @param c              Webhook ID.
     * @param auth           Webhook Auth-Code.
     * @param webhookMessage WebhookMessage itself.
     * @param voiceData      Provided VoiceData used for the Log.
     * @param type           LogTyp.
     */
    public LoggerMessage(Guild g, long c, String auth, WebhookMessage webhookMessage, LoggerVoiceData voiceData, LogTyp type) {
        this.guild = g;
        this.id = c;
        this.authCode = auth;
        this.webhookMessage = webhookMessage;
        this.voiceData = voiceData;
        this.type = type;
    }

    /**
     * Get the Guild of the Log-Message.
     *
     * @return the Guild.
     */
    public Guild getGuild() {
        return guild;
    }

    /**
     * Set the Guild of the Log-Message.
     *
     * @param guild the Guild.
     */
    @Deprecated
    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    /**
     * Webhook ID used for Discord to identificate which Webhook is meant.
     *
     * @return the Webhook ID.
     */
    public long getId() {
        return id;
    }

    /**
     * Change the Webhook ID.
     *
     * @param id the Webhook ID.
     */
    @Deprecated
    public void setId(long id) {
        this.id = id;
    }

    /**
     * The Auth-Code used to authenticate the Webhook Packet.
     *
     * @return the Authentication Code.
     */
    public String getAuthCode() {
        return authCode;
    }

    /**
     * Change the Auth-Code of the Webhook Packet.
     *
     * @param authCode the new Auth-Code.
     */
    @Deprecated
    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    /**
     * Get the current WebhookMessage.
     *
     * @return WebhookMessage.
     */
    public WebhookMessage getWebhookMessage() {
        return webhookMessage;
    }

    /**
     * Change the current Webhook-Message.
     *
     * @param webhookMessage new Webhook-Message.
     */
    public void setWebhookMessage(WebhookMessage webhookMessage) {
        this.webhookMessage = webhookMessage;
    }

    /**
     * The current LogTyp.
     *
     * @return the LogTyp.
     */
    public LogTyp getType() {
        return type;
    }

    /**
     * Change the LogTyp of the current Message.
     *
     * @param type the new LogTyp.
     */
    @Deprecated
    public void setType(LogTyp type) {
        this.type = type;
    }

    /**
     * Check if the Message is canceled or not.
     *
     * @return is the Message canceled.
     */
    public boolean isCanceled() {
        return cancel;
    }

    /**
     * Cancel the LogMessage or "uncancel" the LogMessage.
     *
     * @param cancel should the Message be canceled.
     */
    public void setCanceled(boolean cancel) {
        this.cancel = cancel;
    }

    /**
     * The used RoleData.
     *
     * @return the RoleData.
     */
    public LoggerRoleData getRoleData() {
        return roleData;
    }

    /**
     * Change The used RoleData.
     *
     * @param roleData the new RoleData.
     */
    public void setRoleData(LoggerRoleData roleData) {
        this.roleData = roleData;
    }

    /**
     * The used VoiceData.
     *
     * @return the VoiceData.
     */
    public LoggerVoiceData getVoiceData() {
        return voiceData;
    }

    /**
     * Change the used VoiceData.
     *
     * @param voiceData the new VoiceData.
     */
    public void setVoiceData(LoggerVoiceData voiceData) {
        this.voiceData = voiceData;
    }

    /**
     * The used MemberData.
     *
     * @return the MemberData.
     */
    public LoggerMemberData getMemberData() {
        return memberData;
    }

    /**
     * Change the used MemberData.
     *
     * @param memberData the new MemberData.
     */
    public void setMemberData(LoggerMemberData memberData) {
        this.memberData = memberData;
    }

    // The used Log-Types.
    public enum LogTyp {
        SERVER_JOIN, SERVER_LEAVE, SERVER_INVITE, VC_JOIN, VC_MOVE, VC_LEAVE, NICKNAME_CHANGE, ROLEDATA_CHANGE, MEMBERROLE_CHANGE, CHANNELDATA_CHANGE, USER_BAN, USER_UNBAN, MESSAGE_DELETE, ELSE
    }
}
