package CAP_FIT.TicketManagement.Repository;

import CAP_FIT.TicketManagement.Data.TrainingRecord;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RecordRepository {

  /**
   * 新規カルテ情報の追加が可能
   * @param trainingRecord 新規カルテ情報
   */
  void insertRecord(TrainingRecord trainingRecord);

}
