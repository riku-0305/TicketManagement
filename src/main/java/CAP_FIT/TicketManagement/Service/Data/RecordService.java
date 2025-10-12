package CAP_FIT.TicketManagement.Service.Data;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import CAP_FIT.TicketManagement.Repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

  private final RecordRepository recordRepository;

  @Autowired
  public RecordService(RecordRepository recordRepository) {
    this.recordRepository = recordRepository;
  }

  /**
   * 新規カルテ情報の登録が可能でrecordRepositoryからinsertRecordメソッドを呼び出す
   * @param trainingRecord 新規カルテ情報
   */
  public void searchInsertRecord(TrainingRecord trainingRecord) {
    recordRepository.insertRecord(trainingRecord);
  }
}
