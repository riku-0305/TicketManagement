package CAP_FIT.TicketManagement.Service.Data;

import static org.junit.jupiter.api.Assertions.*;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import CAP_FIT.TicketManagement.Repository.RecordRepository;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

  @Mock
  private RecordRepository recordRepository;

  private RecordService sut;

  @BeforeEach
  void before() {
    sut = new RecordService(recordRepository);
  }

  private TrainingRecord createValidTrainingRecord() {
    return new TrainingRecord(1,"090-1234-5678", LocalDate.now(),"テスト","テスト");
  }

  @Test
  void RecordRepositoryから新しいカルテの登録が可能なinsertRecordメソッドを呼び出せる() {
    sut.searchInsertRecord(createValidTrainingRecord());
    Mockito.verify(recordRepository, Mockito.times(1)).insertRecord(Mockito.any(TrainingRecord.class));
  }
}