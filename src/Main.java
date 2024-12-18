// public class Main {
//         public static void main(String[] args) {
//         // Get settings from the dialog
//         TrackmateParameters.Settings settings = TrackmateParameters.getSettings();

//         if (settings == null) {
//             System.out.println("Settings retrieval was canceled. Exiting program...");
//             System.exit(0);
//         } else {
//             // Log the retrieved settings
//             System.out.println("Input Directory: " + settings.inputDir);
//             System.out.println("Output Directory: " + settings.outputDir);
//             System.out.println("Search Subdirectories: " + settings.searchSubdirs);
//             System.out.println("Save XML: " + settings.saveXML);
//             System.out.println("Number of .tif files found: " + settings.tifFileList.size());
//         }
    

//     }
// }

import java.util.List;
import java.util.Map;






public class Main {
    static {
        net.imagej.patcher.LegacyInjector.preinit();
    }
    public static void main(String[] args){

        TrackmateParameters.Settings settings = TrackmateParameters.getSettings();


        // Test variables, should be acquired by TrackmateParameters
        String testInputDirPath = settings.inputDir;
        String testOutputDirPath = settings.outputDir;
        String trackmateConfigJson = settings.trackmateConfigJSONString;
        boolean searchSubdirs = settings.searchSubdirs;
        // boolean saveXML = settings.saveXML;


        // String testInputDirPath = "C:\\Users\\akmishra\\Desktop\\Test_kinetic_movies";
        // String testOutputDirPath = "C:\\Users\\akmishra\\Desktop\\Testing";
        // String trackmateConfigJson = "C:\\Users\\akmishra\\Desktop\\Batch_Trackmate\\Trackmate_RAM_Crash_fix\\src\\Trackmate_config.json";
        // boolean searchSubdirs = true;



        // Replicate the folder structure
        String stringAutotrackedDir = ReplicateFolderStructure.replicateFolders(testInputDirPath, testOutputDirPath);

        // Sanity check, print out the replicated output folder structure
        System.out.println("stringAutotrackedDir: " + stringAutotrackedDir);

        // Get the tiff files to search
        List<String> tifFileList = FileSearcher.findFilesWithExtensions(
            new String[]{"tif", "tiff"}, 
            testInputDirPath, 
            searchSubdirs
        );
    
        // Sanity check, print out the found tiff files
        System.out.println("tifFileList");
        for (String filePath : tifFileList) {
            System.out.println(filePath);
        }
        
        // Load the JSON as a map
        Map<String, Object> trackmateConfigMap = JsonFileToMap.parseJsonFileToMap(trackmateConfigJson);

        // Run the Kalman Tracking (CSV merging will be performed at the end)
        KalmanTrackerRunner.runKalmanTracking(tifFileList, trackmateConfigMap, testInputDirPath, stringAutotrackedDir);

    }
}
