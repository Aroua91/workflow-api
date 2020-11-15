package com.ncq.devstudio.workflows.errors;

/**
 * This class enumerates errors codes.
 *
 * @author Aroua Souabni
 */
public class ErrorCode {

    private static final int BASE_CATEGORY = 0;

    public static final int SAVE_LOGO_ERROR = BASE_CATEGORY + 1;

    public static final int READ_LOGO_ERROR = BASE_CATEGORY + 2;

    public static final int EMPTY_NAME_ERROR = BASE_CATEGORY + 3;

    public static final int EMPTY_DESCREPTION_ERROR = BASE_CATEGORY + 4;

    public static final int EMPTY_LOGO_ERROR = BASE_CATEGORY + 5;

    public static final int EMPTY_EXT_ERROR = BASE_CATEGORY + 6;

    public static final int STATUS_OUT_OF_BOUND_ERROR = BASE_CATEGORY + 7;

    private static final int BASE_WORKFLOW = 50;

    public static final int EMPTY_WF_NAME_ERROR = BASE_WORKFLOW + 1;

    public static final int EMPTY_WF_DESCREPTION_ERROR = BASE_WORKFLOW + 2;

    public static final int WF_STATUS_OUT_OF_BOUND_ERROR = BASE_WORKFLOW + 3;

    public static final int INVALID_WF_CATEGORY_ERROR = BASE_WORKFLOW + 4;

    public static final int UNDEFINED_WF_CATEGORY_ERROR = BASE_WORKFLOW + 5;

    public static final int INVALID_WF_PAGE_ERROR = BASE_WORKFLOW + 6;
}
