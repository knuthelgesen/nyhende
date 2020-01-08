package no.plasmid.nyhende.filter;

import no.plasmid.nyhende.orientdb.OrientDBTransactionWrapper;
import no.plasmid.nyhende.orientdb.service.TransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

public class OrientDBTransactionFilter extends GenericFilterBean {

    private static final Logger LOG = LoggerFactory.getLogger(OrientDBTransactionFilter.class);

    @Autowired
    private TransactionFactory transactionFactory;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        OrientDBTransactionWrapper transaction = transactionFactory.getTransaction();
        LOG.debug("Created transaction: " + transaction);
        filterChain.doFilter(servletRequest, servletResponse);
        transaction.finish();
    }

}
