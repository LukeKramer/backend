package com.backend.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by lukekramer on 16/08/2016.
 */
@javax.persistence.Entity
public class Loan implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private long maxAmount;
    private long minAmount;

    private Loan(){}

    public long getId() {
        return id;
    }

    public long getMaxAmount() {
        return maxAmount;
    }

    public long getMinAmount() {
        return minAmount;
    }

    public void editMaxAmount(long value)
    {
        this.maxAmount = value;
    }
    public void editMinAmount(long value)
    {
        this.minAmount = value;
    }

    public Loan(Builder Build)
    {
        this.id = Build.id;
        this.maxAmount = Build.maxAmount;
        this.minAmount = Build.minAmount;

    }

    public static class Builder
    {
        private long id;
        private long maxAmount;
        private long minAmount;

        public Builder ID(long value)
        {
            this.id = value;
            return this;
        }
        public Builder maxLoanAmount(Long value)
        {
            this.maxAmount = value;
            return this;
        }

        public Builder minLoanAmount(Long value)
        {
            this.minAmount =value;
            return this;
        }

        public Builder copy(Loan value)
        {
            this.id = value.id;
            this.maxAmount = value.maxAmount;
            this.minAmount = value.minAmount;

            return this;
        }

        public Loan build()
        {
            return new Loan(this);
        }


    }
}
