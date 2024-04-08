package kiwi.test.csv;

import kiwi.test.csv.entity.Category;
import kiwi.test.csv.entity.State;
import kiwi.test.csv.repository.CategoryRepository;
import kiwi.test.csv.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@Component
@RequiredArgsConstructor
public class CsvReader {

    private final StateRepository stateRepository;
    private final CategoryRepository categoryRepository;

    public void readCSV() throws IOException {
        long start = System.currentTimeMillis();
        File file = ResourceUtils.getFile("classpath:static/fraud test.csv");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), UTF_8));

        List<Fraud> frauds = parse(bufferedReader);
        log.info("frauds size : {}", frauds.size());
        long end = System.currentTimeMillis();
        log.info("parsing time : {}", end - start);

        start = System.currentTimeMillis();
        Set<String> states = new HashSet<>();
        frauds.forEach(fraud -> states.add(fraud.getState()));
        states.forEach(state -> stateRepository.save(new State(state)));
        log.info("states size : {}", states.size());

        Set<String> categories = new HashSet<>();
        frauds.forEach(fraud -> categories.add(fraud.getCategory()));
        categories.forEach(category -> categoryRepository.save(new Category(category)));
        log.info("categories size : {}", categories.size());

        Set<String> jobs = new HashSet<>();
        frauds.forEach(fraud -> jobs.addAll(fraud.getJob()));
//        categories.forEach(category -> categoryRepository.save(new Category(category)));
        log.info("jobs size : {}", jobs.size());

        Set<String> merchants = new HashSet<>();
        frauds.forEach(fraud -> merchants.addAll(fraud.getMerchant()));
//        categories.forEach(category -> categoryRepository.save(new Category(category)));
        log.info("merchants size : {}", merchants.size());

        end = System.currentTimeMillis();
        log.info("saving time : {}", end - start);

    }

    private List<Fraud> parse(BufferedReader bufferedReader) throws IOException {
        List<Fraud> parsed = new ArrayList<>();

        StringTokenizer stringTokenizer;
        Fraud fraud;
        String line = bufferedReader.readLine();
        while ((line = bufferedReader.readLine()) != null) {
            stringTokenizer = new StringTokenizer(line, ",");
            fraud = Fraud.builder()
                    .id(parseInt(stringTokenizer.nextToken()))
                    .trans_date_trans_time(stringTokenizer.nextToken())
                    .cc_num(stringTokenizer.nextToken())
                    .merchant(toSet(stringTokenizer))
                    .category(stringTokenizer.nextToken())
                    .amt(Double.valueOf(stringTokenizer.nextToken()))
                    .first(stringTokenizer.nextToken())
                    .last(stringTokenizer.nextToken())
                    .gender(stringTokenizer.nextToken())
                    .street(stringTokenizer.nextToken())
                    .city(stringTokenizer.nextToken())
                    .state(stringTokenizer.nextToken())
                    .zip(Integer.valueOf(stringTokenizer.nextToken()))
                    .latitude(Double.valueOf(stringTokenizer.nextToken()))
                    .longitude(Double.valueOf(stringTokenizer.nextToken()))
                    .city_pop(Integer.valueOf(stringTokenizer.nextToken()))
                    .job(toSet(stringTokenizer))
                    .dob(stringTokenizer.nextToken())
                    .trans_num(stringTokenizer.nextToken())
                    .unix_time(Long.valueOf(stringTokenizer.nextToken()))
                    .merch_lat(Double.valueOf(stringTokenizer.nextToken()))
                    .merch_long(Double.valueOf(stringTokenizer.nextToken()))
                    .is_fraud(stringTokenizer.nextToken())
                    .build();
            parsed.add(fraud);
        }

        return parsed;
    }

    private Set<String> toSet(StringTokenizer stringTokenizer) {
        Set<String> set = new HashSet<>();
        String token = stringTokenizer.nextToken();
        if (token.startsWith("\"")) {
            set.add(token.substring(1).strip());
            while (!(token = stringTokenizer.nextToken()).endsWith("\"")) {
                set.add(token.strip());
            }
            set.add(token.substring(0,token.length()-1).strip());
        } else {
            set.add(token);
        }

        return set;
    }
}
