package com.itstime.allpasstival.utils;

import com.itstime.allpasstival.domain.entity.Festival;
import com.itstime.allpasstival.repository.FestivalRepository;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FestivalCSVParsing {
    private String filePath;
    private BufferedReader bufferedReader;
    private List<String[]> readCSV;
    private int index;

    //This constructor is for read CSV File
    public FestivalCSVParsing(String filePath) throws IOException {
        this.filePath = filePath;
        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.filePath), "UTF-8"));
        readCSV = new ArrayList<>();

        makeList(bufferedReader);
        this.index = 0;
    }

    public void makeList(BufferedReader bufferedReader) throws IOException {
        String line = null;
        while((line = bufferedReader.readLine())!=null) {
            String[] lineContents = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)",-1);

            readCSV.add(lineContents);
        }
    }

    //한 행을 읽음
    public String[] nextRead(){
        if(readCSV.size() == index){
            return null;
        }
        return readCSV.get(index++);
    }

    public static void main(String[] args) throws IOException {
        FestivalCSVParsing festivalCSVParsing = new FestivalCSVParsing("C:\\Users\\HeongJi\\Downloads\\test.txt");
        String[] line=null;
        while((line = festivalCSVParsing.nextRead())!=null){
            int cnt = 0;
            for(String a : line){
                System.out.print(cnt++);
                System.out.print(" ");
                System.out.println(a);
            }
            Festival festival = Festival.builder()
                    .festivalName(line[0])
                    .holdingVenue(line[1])
                    .startDate(line[2])
                    .finishDate(line[3])
                    .content(line[4])
                    .hostOrg(line[5])
                    .hostInst(line[6])
                    .telNum(line[7])
                    .homepAddr(line[9])
                    .streetAddr(line[11])
                    .latitude(line[13])
                    .longitude(line[14])
                    .build();
            System.out.println();
        }
    }
}

