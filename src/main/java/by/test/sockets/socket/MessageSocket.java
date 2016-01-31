package by.test.sockets.socket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import by.test.sockets.domain.json.Message;
import by.test.sockets.socket.decoder.MessageDecoder;
import by.test.sockets.socket.encoder.MessageEncoder;

@ServerEndpoint(value="/messagesocket", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class MessageSocket {

	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

	@OnMessage
	public void broadcastMessage(Message message, Session session) throws IOException, EncodeException {
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(message);
            }
        }
    }

	@OnOpen
	public void onOpen(Session peer) {
		peers.add(peer);
	}

	@OnClose
	public void onClose(Session peer) {
		peers.remove(peer);
	}

}
