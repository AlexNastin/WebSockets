package by.test.sockets.socket;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

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

//	private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());


	
	public MessageSocket() {
	}
	
	private static CopyOnWriteArraySet<Session> poolSessions = new CopyOnWriteArraySet<Session>();
	
	@OnMessage
	public void broadcastMessage(Message message, Session sessionUser) throws IOException, EncodeException {
		System.out.println("broadcastMessage " +sessionUser);
		System.out.println("broadcastMessage " +message.toString());
        for (Session session : poolSessions) {
            if (!session.equals(sessionUser)) {
            	System.out.println("broadcastMessage "+session.toString());
            	session.getBasicRemote().sendObject(message);
            }
        }
    }

	@OnOpen
	public void onOpen(Session session) {
		System.out.println("onOpen " + session.toString());
		poolSessions.add(session);
	}

	@OnClose
	public void onClose(Session session) {
		System.out.println("onClose "+ session.toString());
		poolSessions.remove(session);
	}

}
