/*
 * This file is generated by jOOQ.
 */
package model.generated.tables.records;


import model.generated.tables.Ordine;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class OrdineRecord extends UpdatableRecordImpl<OrdineRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>ORDINE.NUM_ORDINE</code>.
     */
    public void setNumOrdine(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>ORDINE.NUM_ORDINE</code>.
     */
    public Integer getNumOrdine() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>ORDINE.TAVOLO</code>.
     */
    public void setTavolo(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>ORDINE.TAVOLO</code>.
     */
    public Integer getTavolo() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Ordine.ORDINE.NUM_ORDINE;
    }

    @Override
    public Field<Integer> field2() {
        return Ordine.ORDINE.TAVOLO;
    }

    @Override
    public Integer component1() {
        return getNumOrdine();
    }

    @Override
    public Integer component2() {
        return getTavolo();
    }

    @Override
    public Integer value1() {
        return getNumOrdine();
    }

    @Override
    public Integer value2() {
        return getTavolo();
    }

    @Override
    public OrdineRecord value1(Integer value) {
        setNumOrdine(value);
        return this;
    }

    @Override
    public OrdineRecord value2(Integer value) {
        setTavolo(value);
        return this;
    }

    @Override
    public OrdineRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached OrdineRecord
     */
    public OrdineRecord() {
        super(Ordine.ORDINE);
    }

    /**
     * Create a detached, initialised OrdineRecord
     */
    public OrdineRecord(Integer numOrdine, Integer tavolo) {
        super(Ordine.ORDINE);

        setNumOrdine(numOrdine);
        setTavolo(tavolo);
        resetChangedOnNotNull();
    }
}
