/*
 * This file is generated by jOOQ.
 */
package com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.daos;


import com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.Voter;
import com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.records.VoterRecord;

import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;

import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class VoterDao extends DAOImpl<VoterRecord, com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter, UUID> {

    /**
     * Create a new VoterDao without any configuration
     */
    public VoterDao() {
        super(Voter.VOTER, com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter.class);
    }

    /**
     * Create a new VoterDao with an attached configuration
     */
    public VoterDao(Configuration configuration) {
        super(Voter.VOTER, com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter.class, configuration);
    }

    @Override
    public UUID getId(com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>ID BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter> fetchRangeOfId(UUID lowerInclusive, UUID upperInclusive) {
        return fetchRange(Voter.VOTER.ID, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>ID IN (values)</code>
     */
    public List<com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter> fetchById(UUID... values) {
        return fetch(Voter.VOTER.ID, values);
    }

    /**
     * Fetch a unique record that has <code>ID = value</code>
     */
    public com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter fetchOneById(UUID value) {
        return fetchOne(Voter.VOTER.ID, value);
    }

    /**
     * Fetch records that have <code>FIRST_NAME BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter> fetchRangeOfFirstName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Voter.VOTER.FIRST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>FIRST_NAME IN (values)</code>
     */
    public List<com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter> fetchByFirstName(String... values) {
        return fetch(Voter.VOTER.FIRST_NAME, values);
    }

    /**
     * Fetch records that have <code>LAST_NAME BETWEEN lowerInclusive AND upperInclusive</code>
     */
    public List<com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter> fetchRangeOfLastName(String lowerInclusive, String upperInclusive) {
        return fetchRange(Voter.VOTER.LAST_NAME, lowerInclusive, upperInclusive);
    }

    /**
     * Fetch records that have <code>LAST_NAME IN (values)</code>
     */
    public List<com.hexarchbootdemo.adapter.output.persistence.h2.generated_sources.jooq.tables.pojos.Voter> fetchByLastName(String... values) {
        return fetch(Voter.VOTER.LAST_NAME, values);
    }
}
