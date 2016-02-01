package by.test.sockets.socket.decoder;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import by.test.sockets.controller.HomeController;
import by.test.sockets.domain.Post;
import by.test.sockets.domain.json.Message;

public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public Message decode(String string) throws DecodeException {
		JsonObject jsonObject = Json.createReader(new StringReader(string))
				.readObject();
		System.out.println("decode" + string);
		String text = jsonObject.getString("text");
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date = new Date();
		String postDate = dateFormat.format(date);
		HomeController.posts.add(new Post(text, postDate));
		return new Message(jsonObject);
	}

	@Override
	public boolean willDecode(String string) {
		try {
			Json.createReader(new StringReader(string)).readObject();
			return true;
		} catch (JsonException ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public void init(EndpointConfig ec) {
	}

	@Override
	public void destroy() {
	}

}
