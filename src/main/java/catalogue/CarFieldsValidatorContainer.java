package catalogue;

import java.util.HashMap;

class CarFieldsValidatorContainer {

    static final int IS_YEAR = 0;
    static final int IS_STRING = 1;

    static boolean isYearValidation(String str) {
        return true;
    }

    static boolean isStringValidation(String str) {
        return true;
    }

    static boolean noValidation(String str) {
        return true;
    }

    private HashMap<String, CarFieldValidatable> carFieldValidatorMap = new HashMap<String, CarFieldValidatable>();

    CarFieldsValidatorContainer() {

//        for (final String carField : CarCatalogueInfo.DOCUMENT_FIELDS) {
//            //@TODO: finish validation
//            carFieldValidatorMap.put(carField, new CarFieldValidatable() {
//                public boolean run(String carFieldValue) {
//
//                    int validationId = CarCatalogueInfo.DOCUMENT_FIELDS_VALIDATORS_IDS.get(carField);
//
//                    switch (validationId) {
//                        case IS_YEAR:
//                        {
//                            return isYearValidation(carField);
//                        }
//                        case IS_STRING:
//                        {
//                            return isStringValidation(carField);
//                        }
//                        default:
//                        {
//                            //@TODO: throw exception
//                            return noValidation(carField);
//                        }
//                    }
//                }
//            });
//        }
    }
}
