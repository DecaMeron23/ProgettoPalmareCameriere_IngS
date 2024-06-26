/*
 * This file is generated by jOOQ.
 */
package model.generated.tables.records;


import model.generated.tables.PiattoOrdinatoTables;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PiattoOrdinatoRecord extends UpdatableRecordImpl<PiattoOrdinatoRecord> implements Record6<Integer, Integer, Integer, String, Integer, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>PIATTO_ORDINATO.NUM_PIATTO</code>.
     */
    public void setNumPiatto(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PIATTO_ORDINATO.NUM_PIATTO</code>.
     */
    public Integer getNumPiatto() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PIATTO_ORDINATO.NUM_ORDINE</code>.
     */
    public void setNumOrdine(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>PIATTO_ORDINATO.NUM_ORDINE</code>.
     */
    public Integer getNumOrdine() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>PIATTO_ORDINATO.TAVOLO</code>.
     */
    public void setTavolo(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>PIATTO_ORDINATO.TAVOLO</code>.
     */
    public Integer getTavolo() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>PIATTO_ORDINATO.COMMENTO</code>.
     */
    public void setCommento(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>PIATTO_ORDINATO.COMMENTO</code>.
     */
    public String getCommento() {
        return (String) get(3);
    }

    /**
     * Setter for <code>PIATTO_ORDINATO.OCCORRENZE</code>.
     */
    public void setOccorrenze(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>PIATTO_ORDINATO.OCCORRENZE</code>.
     */
    public Integer getOccorrenze() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>PIATTO_ORDINATO.PIATTO</code>.
     */
    public void setPiatto(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>PIATTO_ORDINATO.PIATTO</code>.
     */
    public String getPiatto() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<Integer, Integer, Integer> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Integer, Integer, Integer, String, Integer, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Integer, Integer, Integer, String, Integer, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return PiattoOrdinatoTables.PIATTO_ORDINATO.NUM_PIATTO;
    }

    @Override
    public Field<Integer> field2() {
        return PiattoOrdinatoTables.PIATTO_ORDINATO.NUM_ORDINE;
    }

    @Override
    public Field<Integer> field3() {
        return PiattoOrdinatoTables.PIATTO_ORDINATO.TAVOLO;
    }

    @Override
    public Field<String> field4() {
        return PiattoOrdinatoTables.PIATTO_ORDINATO.COMMENTO;
    }

    @Override
    public Field<Integer> field5() {
        return PiattoOrdinatoTables.PIATTO_ORDINATO.OCCORRENZE;
    }

    @Override
    public Field<String> field6() {
        return PiattoOrdinatoTables.PIATTO_ORDINATO.PIATTO;
    }

    @Override
    public Integer component1() {
        return getNumPiatto();
    }

    @Override
    public Integer component2() {
        return getNumOrdine();
    }

    @Override
    public Integer component3() {
        return getTavolo();
    }

    @Override
    public String component4() {
        return getCommento();
    }

    @Override
    public Integer component5() {
        return getOccorrenze();
    }

    @Override
    public String component6() {
        return getPiatto();
    }

    @Override
    public Integer value1() {
        return getNumPiatto();
    }

    @Override
    public Integer value2() {
        return getNumOrdine();
    }

    @Override
    public Integer value3() {
        return getTavolo();
    }

    @Override
    public String value4() {
        return getCommento();
    }

    @Override
    public Integer value5() {
        return getOccorrenze();
    }

    @Override
    public String value6() {
        return getPiatto();
    }

    @Override
    public PiattoOrdinatoRecord value1(Integer value) {
        setNumPiatto(value);
        return this;
    }

    @Override
    public PiattoOrdinatoRecord value2(Integer value) {
        setNumOrdine(value);
        return this;
    }

    @Override
    public PiattoOrdinatoRecord value3(Integer value) {
        setTavolo(value);
        return this;
    }

    @Override
    public PiattoOrdinatoRecord value4(String value) {
        setCommento(value);
        return this;
    }

    @Override
    public PiattoOrdinatoRecord value5(Integer value) {
        setOccorrenze(value);
        return this;
    }

    @Override
    public PiattoOrdinatoRecord value6(String value) {
        setPiatto(value);
        return this;
    }

    @Override
    public PiattoOrdinatoRecord values(Integer value1, Integer value2, Integer value3, String value4, Integer value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached PiattoOrdinatoRecord
     */
    public PiattoOrdinatoRecord() {
        super(PiattoOrdinatoTables.PIATTO_ORDINATO);
    }

    /**
     * Create a detached, initialised PiattoOrdinatoRecord
     */
    public PiattoOrdinatoRecord(Integer numPiatto, Integer numOrdine, Integer tavolo, String commento, Integer occorrenze, String piatto) {
        super(PiattoOrdinatoTables.PIATTO_ORDINATO);

        setNumPiatto(numPiatto);
        setNumOrdine(numOrdine);
        setTavolo(tavolo);
        setCommento(commento);
        setOccorrenze(occorrenze);
        setPiatto(piatto);
        resetChangedOnNotNull();
    }
}
