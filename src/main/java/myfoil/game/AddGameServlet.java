package myfoil.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import myfoil.db.Dao;
import myfoil.db.EntityManager;
import myfoil.db.GameDao;
import myfoil.dto.GameDto;
import myfoil.model.Game;
import myfoil.service.GameService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

public class AddGameServlet extends HttpServlet {

    private final Dao<Game> dao = new GameDao(
            new EntityManager(System.getenv("DATABASE"), System.getenv("ENDPOINT"))
    );
    private final GameService service = new GameService(dao);
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.err.println("Got new add game request");
        String body = null;
        try {
            body = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        System.err.println("with body: " + body);

        service.save(objectMapper.readValue(body, GameDto.class));
        resp.setStatus(HttpServletResponse.SC_OK);
    }

}


/*
import os
import ydb
import ydb.iam
import boto3

driver = ydb.Driver(
  endpoint=os.getenv('ENDPOINT'),
  database=os.getenv('DATABASE'),
  credentials=ydb.iam.MetadataUrlCredentials(),
)

# Wait for the driver to become active for requests.

driver.wait(fail_fast=True, timeout=5)

# Create the session pool instance to manage YDB sessions.
pool = ydb.SessionPool(driver)

def execute_query(session):
  # Create the transaction and execute query.
  return session.transaction().execute(
    'select 1 as cnt;',
    commit_tx=True,
    settings=ydb.BaseRequestSettings().with_timeout(3).with_operation_timeout(2)
  )

def handler(event, context):
  bucket_name = event['messages'][0]['details']['bucket_id']
  object_name = event['messages'][0]['details']['object_id']
  boto_session = boto3.session.Session()
  s3 = boto_session.client(
    service_name='s3',
    endpoint_utl='https://storage.yandexcloud.net'
  )
  obj = s3.get_object(Bucket=bucket_name, Key=object_name)
  print(object_name)
  print(obj)
  # result = pool.retry_operation_sync(execute_query)
  return {
    'statusCode': 200,
    'body': str(result[0].rows[0].cnt == 1),
  }

  ydb
boto3


AWS_ACCESS_KEY_ID
AWS_SECRET_ACCESS_KEY
AWS_DEFAULT_REGION
*/