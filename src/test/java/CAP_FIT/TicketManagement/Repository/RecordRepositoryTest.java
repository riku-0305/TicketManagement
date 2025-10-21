package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

@MybatisTest
class RecordRepositoryTest {

  @Autowired
  private RecordRepository sut;


  @Test
  void idで指定したユーザーのカルテ情報の取得が可能() {
    List<TrainingRecord> actual = sut.selectRecordList("090-1234-5678");

    Assertions.assertEquals(1, actual.size());
  }

  @Test
  void ユーザーのカルテ情報の登録が可能() {
    TrainingRecord newTrainingRecord = new TrainingRecord(1,"080-1234-5678", LocalDate.now(), "テスト", "テスト2");

    sut.insertRecord(newTrainingRecord);

    List<TrainingRecord> actual = sut.selectRecordList(newTrainingRecord.getUserId());

    Assertions.assertEquals(1, actual.size());
  }
}