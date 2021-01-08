import com.fasterxml.jackson.databind.ObjectMapper;
import outputdata.OutputData;
import processing.ProcessInputData;
import readentities.InputData;

import java.io.File;

/** Entry point to the simulation */
public final class Main {

  private Main() {}

  /**
   * Main function which reads the input file and starts simulation
   *
   * @param args input and output files
   * @throws Exception might error when reading/writing/opening files, parsing JSON
   */
  public static void main(final String[] args) throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    InputData inputData = objectMapper.readValue(new File(args[0]), InputData.class);
    ProcessInputData processInputData = new ProcessInputData(inputData);
    processInputData.start();
    OutputData outputData = new OutputData();
    outputData.createOutputData(processInputData.getInputData());
    //        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.writeValue(new File(args[1]), outputData);
  }
}
