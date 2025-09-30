/**
 * This class controls high-level logic for the reading-list comparison feature.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class Controller {

    // Names of the people whose lists we are comparing.
    String namePerson1;

    String namePerson2;

    private UserInterface userInterface;

    private ReadingListDataStore readingListDataStore;  // Holds the reading lists.

    private ReadingListAnalyzer analyzer;  // Determines which books are in common between each list.

    private StatisticsGenerator statisticsGenerator;  // Generates information regarding the comparison.

    private StatisticsDataStore statisticsDataStore;  // Holds statistics about the comparison.

    public Controller(String namePerson1, Book[] readingListPerson1, String namePerson2, Book[] readingListPerson2) {

        userInterface = new UserInterface(namePerson1, namePerson2);

        readingListDataStore = new ReadingListDataStore(readingListPerson1, readingListPerson2);

        analyzer = new ReadingListAnalyzer(readingListDataStore);

        statisticsGenerator = new StatisticsGenerator(readingListDataStore);

        statisticsDataStore = new StatisticsDataStore();
    }

    // Getters and setters.

    public String getNamePerson1() {
        return namePerson1;
    }

    public String getNamePerson2() {
        return namePerson2;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public ReadingListAnalyzer getAnalyzer() {
        return analyzer;
    }

    public StatisticsGenerator getStatisticsGenerator() {
        return statisticsGenerator;
    }

    public StatisticsDataStore getStatisticsDataStore() {
        return statisticsDataStore;
    }

    // The central method of the comparison feature.
    public void performComparison() {

        getAnalyzer().logBooksInCommon();

        StatisticsGenerator statsGenerator = getStatisticsGenerator();
        statsGenerator.setBooksInCommon(getAnalyzer().getBooksInCommon());
        statsGenerator.computeInterestStatistics();  // Compute all statistics about reading lists and interests.
        
        StatisticsDataStore statsDataStore = statsGenerator.getStatisticsDataStore();
        statsDataStore.setStatistics(statsGenerator.compileInterestStatistics());  // Store all statistics.

        getUserInterface().displayInterestStatistics(statsDataStore.getStatistics());  // Display statistics to the user.
    }

}
