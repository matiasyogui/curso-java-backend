package com.myogui.mongodbcrud;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myogui.mongodbcrud.model.Product;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MongodbCrudApplicationTests {

	private String url;
	@LocalServerPort
	private int port;
	private ObjectMapper mapper = new ObjectMapper();

	private static Long start;
	private static Long end;

	@Autowired
	private TestRestTemplate restTemplate;

	//MENSAJES PREVIOS A LOS TESTS
	@BeforeAll
	static void setUp() {
		start = System.nanoTime();
		System.out.println("Mensaje previo a la ejecution de todos los tests");
	}

	@BeforeEach
	void init() {
		url = String.format("http://localhost:%d/mongodbcrud/", port);
		System.out.println("Mensaje previo a la ejecucion de cada test");
	}

	@AfterAll
	static void endTests() {
		end = System.nanoTime();
		System.out.println("Se ejecutaron todos los tests con un tiempo de " + (TimeUnit.NANOSECONDS.toMillis(end - start)));
	}

	//TESTS CON RESTTEMPLATE
	@Test
	void getAllProducts() {
		var uriTest = String.format("%s%s", url, "products");
		var result = this.restTemplate.getForObject(uriTest, List.class);

		Assertions.assertNotNull(result, "Lista de productos no vacia");
		Assertions.assertEquals(result.size(), 9, "Lista de productos con 6 productos");
	}

	@Test
	void createProduct() {
		var uriTest = String.format("%s%s", url, "products");
		var product = new Product("Fideos", "Harina", 1111, 100);

		var result = this.restTemplate.postForObject(uriTest, product, Product.class);

		Assertions.assertNotNull(result, "Producto no nulo.");
		Assertions.assertEquals("Fideos", result.getNombre(), "Nombre del productos creado correctamente.");
	}

	//TESTS CON HTTPREQUEST
	@Test
	void getAllProductsHttpRequest() throws IOException {
		var uriTest = String.format("%s%s", url, "products");

		var request = new HttpGet(uriTest);
		var httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.isTrue(httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value(), "Response status ok.");
	}

	@Test
	public void getAllProductsHttpRequestPayload() throws IOException {
		var uriTest = String.format("%s%s", url, "products");

		var request = new HttpGet(uriTest);
		var httpResponse =
				HttpClientBuilder.create().build().execute(request);

		String content = EntityUtils.toString(httpResponse.getEntity());
		var messageResult = mapper.readValue(content, List.class);

		Assertions.assertNotNull(messageResult, "Lista de productos no nula");
		Assertions.assertEquals(9, messageResult.size(), "Cantidad de productos = 7");
	}
}
