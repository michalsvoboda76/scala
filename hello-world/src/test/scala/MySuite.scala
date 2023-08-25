// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
  test("example test that succeeds") {
    val obtained = 42
    val expected = 42
    assertEquals(obtained, expected)
  }

  test("test factorial") {
    val actual = factorial(5)
    assertEquals(obtained = actual, expected = 120)
  }
}
