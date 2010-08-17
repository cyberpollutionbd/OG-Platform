/**
 * Copyright (C) 2009 - 2010 by OpenGamma Inc.
 *
 * Please see distribution for license.
 */
package com.opengamma.timeseries;

import java.util.List;

import javax.time.calendar.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opengamma.engine.historicaldata.HistoricalDataSource;
import com.opengamma.id.IdentifierBundle;
import com.opengamma.id.UniqueIdentifier;
import com.opengamma.util.ArgumentChecker;
import com.opengamma.util.timeseries.localdate.ArrayLocalDateDoubleTimeSeries;
import com.opengamma.util.timeseries.localdate.LocalDateDoubleTimeSeries;
import com.opengamma.util.tuple.ObjectsPair;
import com.opengamma.util.tuple.Pair;


/**
 * A {@code TimeSeriesSource} implemented using an underlying {@code TimeSeriesMaster}.
 * <p>
 * The {@link HistoricalDataSource} interface provides timeseries to the engine via a narrow API.
 * This class provides the source on top of a standard {@link TimeSeriesMaster}.
 */
public class MasterTimeSeriesSource implements HistoricalDataSource {
  private static final Logger s_logger = LoggerFactory.getLogger(MasterTimeSeriesSource.class);
  /**
   * The timeseries master.
   */
  private final TimeSeriesMaster _timeSeriesMaster;
  /**
   * The timeseries request resolver
   */
  private final TimeSeriesResolver _timeSeriesResolver;

  /**
   * @param timeSeriesMaster the timeseries master, not-null
   * @param tsResolver the _timeSeries resolver, not-null
   */
  public MasterTimeSeriesSource(TimeSeriesMaster timeSeriesMaster, TimeSeriesResolver tsResolver) {
    ArgumentChecker.notNull(timeSeriesMaster, "timeSeriesMaster");
    ArgumentChecker.notNull(tsResolver, "timeSeriesResolver");
    _timeSeriesMaster = timeSeriesMaster;
    _timeSeriesResolver = tsResolver;
  }

  protected TimeSeriesMaster getTimeSeriesMaster() {
    return _timeSeriesMaster;
  }
  
  @Override
  public Pair<UniqueIdentifier, LocalDateDoubleTimeSeries> getHistoricalData(IdentifierBundle identifiers, String dataSource, String dataProvider, String dataField) {
    return getHistoricalData(identifiers, dataSource, dataProvider, dataField, null, null);
  }

  @Override
  public Pair<UniqueIdentifier, LocalDateDoubleTimeSeries> getHistoricalData(IdentifierBundle identifiers, String dataSource, String dataProvider, String field, LocalDate start, LocalDate end) {
    ArgumentChecker.notNull(identifiers, "identifiers");
    ArgumentChecker.notNull(dataSource, "dataSource");
    ArgumentChecker.notNull(dataProvider, "dataProvider");
    ArgumentChecker.notNull(field, "field");
    
    TimeSeriesRequest request = new TimeSeriesRequest();
    request.setIdentifiers(identifiers);
    request.setDataSource(dataSource);
    request.setDataProvider(dataProvider);
    request.setDataField(field);
    request.setStart(start);
    request.setEnd(end);
    request.setLoadTimeSeries(true);
    
    LocalDateDoubleTimeSeries timeseries = new ArrayLocalDateDoubleTimeSeries();
    TimeSeriesSearchResult searchResult = getTimeSeriesMaster().searchTimeSeries(request);
    List<TimeSeriesDocument> documents = searchResult.getDocuments();
    UniqueIdentifier uid = null;
    if (!documents.isEmpty()) {
      if (documents.size() > 1) {
        Object[] param = new Object[]{identifiers, dataSource, dataProvider, field, start, end};
        s_logger.warn("multiple timeseries return for identifiers={}, dataSource={}, dataProvider={}, dataField={}, start={} end={}", param);
      }
      TimeSeriesDocument timeSeriesDocument = documents.get(0);
      timeseries = timeSeriesDocument.getTimeSeries();
      uid = timeSeriesDocument.getUniqueIdentifier();
    }
    return new ObjectsPair<UniqueIdentifier, LocalDateDoubleTimeSeries>(uid, timeseries);
  }

  @Override
  public LocalDateDoubleTimeSeries getHistoricalData(UniqueIdentifier uid) {
    return getHistoricalData(uid, null, null);
  }

  @Override
  public LocalDateDoubleTimeSeries getHistoricalData(UniqueIdentifier uid, LocalDate start, LocalDate end) {
    ArgumentChecker.notNull(uid, "Identifier");
    TimeSeriesRequest request = new TimeSeriesRequest();
    request.setLoadTimeSeries(true);
    request.setStart(start);
    request.setEnd(end);
    request.setTimeSeriesId(uid);
    request.setLoadTimeSeries(true);
    
    LocalDateDoubleTimeSeries result = new ArrayLocalDateDoubleTimeSeries();
    TimeSeriesSearchResult searchResult = getTimeSeriesMaster().searchTimeSeries(request);
    List<TimeSeriesDocument> documents = searchResult.getDocuments();
    if (!documents.isEmpty()) {
      if (documents.size() > 1) {
        s_logger.warn("multiple timeseries return for uid={}", uid);
      }
      result = documents.get(0).getTimeSeries();   
    }
    return result;
  }

  @Override
  public Pair<UniqueIdentifier, LocalDateDoubleTimeSeries> getHistoricalData(IdentifierBundle identifiers) {
    return getHistoricalData(identifiers, null, null);
  }

  @Override
  public Pair<UniqueIdentifier, LocalDateDoubleTimeSeries> getHistoricalData(IdentifierBundle identifiers, LocalDate start, LocalDate end) {
    ArgumentChecker.isTrue(identifiers != null && !identifiers.getIdentifiers().isEmpty(), "Cannot get historical data with null/empty identifiers");
    TimeSeriesMetaData metaData = _timeSeriesResolver.resolve(identifiers);
    Pair<UniqueIdentifier, LocalDateDoubleTimeSeries> result = new ObjectsPair<UniqueIdentifier, LocalDateDoubleTimeSeries>(null, new ArrayLocalDateDoubleTimeSeries());
    if (metaData != null) {
      result = getHistoricalData(identifiers, metaData.getDataSource(), metaData.getDataProvider(), metaData.getDataField(), start, end);
    } 
    return result;
  }
  
  

}
