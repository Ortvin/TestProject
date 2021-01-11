package org.example.Buisness;

import org.example.Dao.ConstructionyearEntityDAO;
import org.example.Dao.ObjectTypeEntityDAO;
import org.example.Dao.SizeEntityDAO;
import org.example.Entity.ConstructionyearEntity;
import org.example.Entity.ObjecttypeEntity;
import org.example.Entity.SizeEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

public class Calculate {

    float contractSum;
    int countDay;
    double coefObjectType;
    double coefConstructionYear;
    double coefSize;
    LocalDate startContractDate;
    LocalDate endContractDate;

    public Calculate() {
    }

    public Calculate(float contractSum, LocalDate startContractDate, LocalDate endContractDate, double coefObjectType, double coefConstructionYear, double coefSize) {
        this.contractSum = contractSum;
        this.startContractDate = startContractDate;
        this.endContractDate = endContractDate;
        this.coefObjectType = coefObjectType;
        this.coefConstructionYear = coefConstructionYear;
        this.coefSize = coefSize;
    }

    public double getCoefObjectType(String objectTypeName){
        double coefObjectType = 0.0D;
        List<ObjecttypeEntity> list = new ObjectTypeEntityDAO().getObjectTypeByName(objectTypeName);
        for (ObjecttypeEntity s : list) {
            coefObjectType = s.getObjecTypeRate();
        }

        return coefObjectType;
    }

    public double getCoefConstructionYear (Integer constructionYear){
        double coefConstructionYear = 0;
        List<ConstructionyearEntity> list = new ConstructionyearEntityDAO().getConstructionyearByPeriod(constructionYear);
        for (ConstructionyearEntity s : list) {
            coefConstructionYear = s.getConstructionYearCoefficient();
        }
        return coefConstructionYear;
    }

    public double getCoefSize (Double size){
        double coefSize = 0;
        List<SizeEntity> list = new SizeEntityDAO().getSizeEntityBySize(size);
        for (SizeEntity s : list) {
            coefSize = s.getSizeCoefficient();
        }
        return coefSize;
    }

    public double getInsuranceFee(){
        Period period = Period.between(startContractDate, endContractDate);
        int countDay = period.getDays() + 1;
        return contractSum / countDay * coefObjectType * coefConstructionYear * coefSize ;
    }


}
