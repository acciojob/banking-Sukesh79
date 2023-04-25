package com.driver;

import java.util.Arrays;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        if(balance < 5000) throw new Exception("Insufficient Balance");
        this.tradeLicenseId = tradeLicenseId;
    }

    public String getTradeLicenseId(){
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        for(int i=0; i< tradeLicenseId.length()-1; i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                if(newLicenceId()) break;
                else throw new Exception("Valid License can not be generated");
            }
        }
    }

    private boolean newLicenceId(){
        int[][] freq = new int[26][2];
        for(int i=0; i<26; i++) freq[i][0] = i;
        for(char ch:tradeLicenseId.toCharArray()) freq[ch -'A'][1]++;
        Arrays.sort(freq,(a, b) -> b[1]-a[1]);
        if(tradeLicenseId.length()%2 == 0 && freq[0][1]>tradeLicenseId.length()/2) return false;
        if(tradeLicenseId.length()%2 == 1 && freq[0][1] > (getTradeLicenseId().length()/2+1)) return false;

        int idx = 0;
        char[] newId = new char[tradeLicenseId.length()];
        for(int i=0; i<newId.length; i+=2){
            if(freq[idx][1] == 0) idx++;
            newId[i] = (char)(freq[idx][0]+'A');
            freq[idx][1]--;
        }

        for(int i=1; i<newId.length; i+=2){
            if(freq[idx][1] == 0) idx++;
            newId[i] = (char)(freq[idx][0]+'A');
            freq[idx][1]--;
        }

        tradeLicenseId = String.valueOf(newId);
        return true;
    }
}