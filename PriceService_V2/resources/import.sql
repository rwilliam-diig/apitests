DELETE FROM baseprice;
DELETE FROM saletax;
DELETE FROM priceset;
DELETE FROM productdiscount;
DELETE FROM bundle;
DELETE FROM bundleproducts;
DELETE FROM bundlediscount;

INSERT INTO saletax (id, taxCode, percent, recordState) VALUES (1, 'VAT', 0.175, 1);
INSERT INTO saletax (id, taxCode, percent, recordState) VALUES (2, 'VAT2', 0.20, 1);

INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(1,  1, 'FORM12',  'MB', 'GBP', 10.0, 'GBP', 20.0, 'GBP', 10.0, 0.5, 1, 1,1);
INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(2,  1, 'FORM13',  'MB', 'GBP', 11.0, 'GBP', 22.0, 'GBP', 11.0, 0.5, 1, 1,1);
INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(3,  1, 'FORM14',  'MB', 'GBP', 12.0, 'GBP', 24.0, 'GBP', 12.0, 0.5, 1, 1,1);
INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(4,  1, 'FORM15',  'MB', 'GBP', 13.0, 'GBP', 26.0, 'GBP', 13.0, 0.5, 1, 1,1);

INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(5,  1, 'FORM12A',  'QA1', 'GBP', 10.0, 'GBP', 20.0, 'GBP', 10.0, 0.5, 1, 1,1);
INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(6,  1, 'FORM13A',  'QA1', 'GBP', 11.0, 'GBP', 22.0, 'GBP', 11.0, 0.5, 1, 1,1);
INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(7,  1, 'FORM14A',  'QA1', 'GBP', 12.0, 'GBP', 24.0, 'GBP', 12.0, 0.5, 1, 1,1);
INSERT INTO baseprice(id, versionId, productId, businessUnitId, cost_ccy, cost_amt, retailprice_ccy, retailprice_amt, marginamount_ccy, marginamount_amt, marginPercent, marginBasis, taxCode, recordState) VALUES(8,  1, 'FORM15A',  'QA1', 'GBP', 13.0, 'GBP', 26.0, 'GBP', 13.0, 0.5, 1, 1,1);



INSERT INTO priceset(id, versionId, businessUnitId, priceSetStatus, validFrom, validTo, recordState) VALUES (1, 1, "MB", 1, null, null, 1);
INSERT INTO priceset(id, versionId, businessUnitId, priceSetStatus, validFrom, validTo, recordState) VALUES (2, 1, "QA1", 1, null, null, 1);
--INSERT INTO priceset(id, versionId, businessUnitId, priceSetStatus, validFrom, validTo, recordState) VALUES (3, 1, "QA1", 1, null, null, 1);
--INSERT INTO priceset(id, versionId, businessUnitId, priceSetStatus, validFrom, validTo, recordState) VALUES (4, 1, "QA3", 1, null, null, 1);
--INSERT INTO priceset(id, versionId, businessUnitId, priceSetStatus, validFrom, validTo, recordState) VALUES (5, 1, "QA4", 1, null, null, 1);

INSERT INTO productdiscount(id, versionId, productId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (1,1, "FORM12", "MB", "CUSTOMER1", "", 0.25, 'GBP', 5.0, 1,1);
INSERT INTO productdiscount(id, versionId, productId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (2,1, "FORM12A", "QA1", "TESTCUSTOMER1", "", 0.25, 'GBP', 5.0, 1,1);
--INSERT INTO productdiscount(id, versionId, productId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (3,1, "FORM13A", "QA1", "TESTCUSTOMER2", "", 0.50, 'GBP', 5.0, 1,1);
--INSERT INTO productdiscount(id, versionId, productId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (4,1, "FORM14A", "QA3", "TESTCUSTOMER3", "", 0.75, 'GBP', 5.0, 1,1);
--INSERT INTO productdiscount(id, versionId, productId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (5,1, "FORM15A", "QA4", "TESTCUSTOMER4", "", 1.00, 'GBP', 5.0, 1,1);

INSERT INTO bundle(id, bundleId, businessUnitId, recordState) VALUES (1, "CUSTOMER_BUNDLE1", "MB", 1);
INSERT INTO bundle(id, bundleId, businessUnitId, recordState) VALUES (2, "CUSTOMER_BUNDLE2", "MB", 1);
INSERT INTO bundle(id, bundleId, businessUnitId, recordState) VALUES (3, "TEST_CUSTOMER_BUNDLE1", "QA1", 1);
INSERT INTO bundle(id, bundleId, businessUnitId, recordState) VALUES (4, "TEST_CUSTOMER_BUNDLE2", "QA1", 1);
--INSERT INTO bundle(id, bundleId, businessUnitId, recordState) VALUES (5, "TEST_CUSTOMER_BUNDLE3", "QA3", 1);
--INSERT INTO bundle(id, bundleId, businessUnitId, recordState) VALUES (6, "TEST_CUSTOMER_BUNDLE4", "QA4", 1);


INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (1, "FORM12A", 0);
INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (1, "FORM13A", 1);
INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (2, "FORM14A", 0);
INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (2, "FORM15A", 1);

--INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (3, "FORM14A", 1);
--INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (4, "FORM15A", 1);
--INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (5, "FORM14A", 1);
--INSERT INTO bundleproducts(bundleid, elt, idx) VALUE (6, "FORM15A", 1);

INSERT INTO bundlediscount(id, versionId, bundleId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (1, 1, 1, "MB", "CUSTOMER3", "", 0.25, 'GBP', 5.0, 1,1);
INSERT INTO bundlediscount(id, versionId, bundleId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (2, 1, 2, "MB", "CUSTOMER3", "", 0.30, 'GBP', 5.0, 1,1);
INSERT INTO bundlediscount(id, versionId, bundleId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (3, 1, 3, "QA1", "TESTCUSTOMER3", "", 0.25, 'GBP', 5.0, 1,1);
INSERT INTO bundlediscount(id, versionId, bundleId, businessUnitId, customerId, customerGroupId, discountPercent, discountvalue_ccy, discountvalue_amt, discountBasis, recordState) VALUES (4, 1, 4, "QA1", "TESTCUSTOMER4", "", 0.30, 'GBP', 5.0, 1,1);