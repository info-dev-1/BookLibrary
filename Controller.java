/**
 * This class controls high-level logic for the reading-list comparison feature.
 * 
 * @author info-dev-1
 * @since 9/27/2025
 */
public class Controller {

    String namePerson1;

    String namePerson2;

    private UserInterface userInterface;

    private ReadingListDataStore readingListDataStore;

    private ReadingListAnalyzer analyzer;

    private StatisticsGenerator statisticsGenerator;

    private StatisticsDataStore statisticsDataStore;


    public Controller(String namePerson1, Book[] readingListPerson1, String namePerson2, Book[] readingListPerson2) {

        userInterface = new UserInterface(namePerson1, namePerson2);

        readingListDataStore = new ReadingListDataStore(readingListPerson1, readingListPerson2);

        analyzer = new ReadingListAnalyzer(readingListDataStore);

        statisticsGenerator = new StatisticsGenerator(readingListDataStore);

        statisticsDataStore = new StatisticsDataStore();
    }

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
        statsGenerator.computeInterestStatistics();
        
        StatisticsDataStore statsDataStore = statsGenerator.getStatisticsDataStore();
        statsDataStore.setStatistics(statsGenerator.compileInterestStatistics());

        getUserInterface().displayInterestStatistics(statsDataStore.getStatistics());
    }

}
