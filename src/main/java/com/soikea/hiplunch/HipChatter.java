package com.soikea.hiplunch;



import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

/**
 * Created by penni on 18/08/14.
 */
public class HipChatter {

	private static final String ROOM = "748322";
	private static final String FROM = "LunchBot";
	private static final String MESSAGE_FORMAT = "html";
	private static final String NOTIFY = "0";
	private static final String COLOR = "green";
	private static final String API_KEY = "ckjUYHTlmUaONzhp96J64aJ68HnstVHLIzWXCGs2";

	public void sendMessage(String message) {

		try {
			ClientConfig clientConfig = new DefaultClientConfig();


			clientConfig.getFeatures().put(
					JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);

			Client client = Client.create(clientConfig);

//			WebResource authResource = client.resource("https://api.hipchat.com/v2/oauth/token");
//
//			ClientResponse authResponse = authResource.accept("application/json")
//					.type("application/json").post(ClientResponse.class, createAuthJsonString());
//
//			System.out.println(authResource);
//
//			System.out.println(authResponse);
//
			WebResource webResource = client
					.resource("https://api.hipchat.com/v2/room/" + ROOM + "/notification");

			ClientResponse response = webResource.accept("application/json")
					.type("application/json").post(ClientResponse.class, createMessageJsonString(message));

			System.out.println(createMessageJsonString(message));

			System.out.println(webResource);

			System.out.println(response);

//			WebTarget webTarget = client.target("https://api.hipchat.com/v2/");
//			WebTarget resourceWebTarget = webTarget.path("room/" + ROOM + "/notification");
//			WebTarget messageWebTargetWithQueryParam = resourceWebTarget
////					.queryParam("room", ROOM)
//					.queryParam("auth_token", API_KEY)
//					.queryParam("from", FROM)
//					.queryParam("message_format", MESSAGE_FORMAT)
//					.queryParam("notify", NOTIFY)
//					.queryParam("color", COLOR)
//					.queryParam("message", message);
//
//
//			Invocation.Builder invocationBuilder =
//					messageWebTargetWithQueryParam.request(MediaType.APPLICATION_JSON);
//
//			System.out.println(messageWebTargetWithQueryParam.toString());
//			invocationBuilder.header("some-header", "true");

//			Response response = invocationBuilder.post();
//			System.out.println(response.getStatus());
//			System.out.println(response.readEntity(String.class));

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	private String createAuthJsonString() {
		return "{"
				+ "auth_token=" + API_KEY
				+ ", grant_type=" + "client_credentials"
				+ ", scope=" + "send_notification"

				+ '}';

	}

	private String createMessageJsonString(String message) {

		return "{"
				+ "auth_token=" + API_KEY
				+ ", from=" + FROM
				+ ", message_format=" + MESSAGE_FORMAT
				+ ", notify=" + NOTIFY
				+ ", color=" + COLOR
				+ ", message=" + message
				+ "}";

	}

}
