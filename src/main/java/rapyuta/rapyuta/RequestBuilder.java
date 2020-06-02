package rapyuta.rapyuta;

import java.util.HashMap;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class RequestBuilder {
	private Map<String, String> headers;
	private Map<String, String> params;

	private String endPoint;

	private Object request;

	private Map<String, String> getHeaders() {
		return headers;
	}

	public RequestBuilder setHeaders(Map<String, String> headers) {
		this.headers = headers;
		return this;
	}

	private String getEndPoint() {
		return endPoint;
	}

	public RequestBuilder setEndPoint(String endPoint) {
		this.endPoint = endPoint;
		return this;
	}

	private Object getRequestBody() {
		return request;
	}

	public RequestBuilder setRequestBody(Object request) {
		this.request = request;
		return this;
	}

	private Map<String, String> getParams() {
		return params;
	}

	public RequestBuilder setParams(HashMap<String, String> params) {
		this.params = params;
		return this;
	}

	public Response executeGet() {
		//RestAssured.defaultParser = Parser.JSON;  
		RequestSpecification request = RestAssured.given().when().with();
		if (this.getEndPoint() != null) {
			if (this.getHeaders() != null) {
				request = request.headers(getHeaders());
			}
			if (this.getParams() != null)
				request = request.params(getParams());
			return request.get(getEndPoint());
		}
		return null;
	}

	public Response executePost() {
		RequestSpecification request = RestAssured.given().when().with()
				.contentType("application/json");
		if (this.getEndPoint() != null && this.getRequestBody() != null) {
			if (this.getHeaders() != null) {
				
				request = request.headers(getHeaders());
			}

			return request.body(getRequestBody()).post(getEndPoint()).then().extract().response();
		}
		return null;
	}

}
