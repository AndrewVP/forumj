<?
include_once 'classes/ua/com/diletant/web/constants.php';
include_once 'classes/ua/com/diletant/web/constraint.php';
include_once 'classes/ua/com/diletant/web/constraintfailure.php';
class Request {
	private $_arGetVars;
	private $_arPostVars;
	private $_arCookieVars;
	private $_arRequestVars;
	private $_objOriginalRequestObject;

	private $_blIsRedirectFollowingConstraintFailure;

	private $_blRedirectOnConstraintFailure;
	private $_strConstraintFailureRedirectTargetURL;
	private $_strConstraintFailureDefaultRedirectTargetURL;

	private $_arObjParameterMethodConstraintHash;
	private $_arObjConstraintFailure;
	private $_hasRunConstraintTests;


	function __construct($check_for_cookie = true) {
		// Import variables
		global $_REQUEST;
		global $_GET;
		global $_POST;
		global $_COOKIE;
		$this->_arGetVars = $_GET;
		$this->_arPostVars = $_POST;
		$this->_arCookieVars = $_COOKIE;
		$this->_arRequestVars = $_REQUEST;
		if ($check_for_cookie) {
			if ($this->_arCookieVars["phprqcOriginalRequestObject"]) {
				$cookieVal = $this->_arRequestVars["phprqcOriginalRequestObject"];
				$this->_blIsRedirectFollowingConstraintFailure = true;
				if (strlen($cookieVal) > 0) {
					$strResult = setcookie ("phprqcOriginalRequestObject", "", time() - 3600, "/");
					$origObj = unserialize(stripslashes($cookieVal));
					$this->_objOriginalRequestObject = &$origObj;
					$this->_arRequestVars["phprqcOriginalRequestObject"] = "";
					$this->_arGetVars["phprqcOriginalRequestObject"] = "";
					$this->_arPostVars["phprqcOriginalRequestObject"] = "";
				};
				$this->_blIsRedirectOnConstraintFailure  = true;
			} else {
				$this->_blIsRedirectOnConstraintFailure  = false;
			};
		} else {
			$this->_blIsRedirectOnConstraintFailure  = false;
		};
		$this->_arObjParameterMethodConstraintHash = Array();
		$this->_arObjConstraintFailure = Array();
		$this->_blHasRunConstraintTests = false;
	}

	function IsRedirectFollowingConstraintFailure() {
		return($this->_blIsRedirectOnConstraintFailure);
	}

	function GetOriginalRequestObjectFollowingConstraintFailure() {
		if ($this->_blIsRedirectOnConstraintFailure) {
			return($this->_objOriginalRequestObject);
		};
	}

	function SetRedirectOnConstraintFailure($blTrueOrFalse) {
		$this->_blRedirectOnConstraintFailure  = $blTrueOrFalse;
	}

	function SetConstraintFailureRedirectTargetURL($strURL) {
		$this->_strConstraintFailureRedirectTargetURL = $strURL;
	}

	function SetConstraintFailureDefaultRedirectTargetURL($strURL) {
		$this->_strConstraintFailureDefaultRedirectTargetURL = $strURL;
	}

	function GetParameterValue($strParameter) {
		if (isset($this->_arRequestVars[$strParameter])){
			return $this->_arRequestVars[$strParameter];
		}else{
			return NULL;
		}
	}

	function GetParameters() {
		return($this->_arRequestVars);
	}

	function GetCookies() {
		return($this->_arCookieVars);
	}

	function GetPostVariables() {
		return($this->_arPostVariables);
	}

	function GetGetVariables() {
		return($this->_arGetVariables);
	}

	function AddConstraint($strParameter, $intMethod, $objConstraint, $blRequired, $default) {
		$newHash["PARAMETER"] = $strParameter;
		$newHash["METHOD"] = $intMethod;
		$newHash["CONSTRAINT"] = $objConstraint;
		$newHash["REQUIRED"] = $blRequired;
		$newHash["DEFAULT"] = $default;
		$this->_arObjParameterMethodConstraintHash[] = $newHash;
	}

	function TestConstraints() {
		$this->_blHasRunConstraintTests = true;
		$anyFail = false;
		for ($i=0; $i<=sizeof($this->_arObjParameterMethodConstraintHash)-1; $i++) {
			$strThisParameter = $this->_arObjParameterMethodConstraintHash[$i]["PARAMETER"];
			$intThisMethod = $this->_arObjParameterMethodConstraintHash[$i]["METHOD"];
			$objThisConstraint = $this->_arObjParameterMethodConstraintHash[$i]["CONSTRAINT"];
			$blRequired = $this->_arObjParameterMethodConstraintHash[$i]["REQUIRED"];
			$default = $this->_arObjParameterMethodConstraintHash[$i]["DEFAULT"];
			$varActualValue = NULL;
			if ($intThisMethod == Constraint::$VERB_METHOD_COOKIE) {
				if (isset($this->_arCookieVars[$strThisParameter])){
					$varActualValue = $this->_arCookieVars[$strThisParameter];
				}
			};
			if ($intThisMethod == Constraint::$VERB_METHOD_GET) {
				if (isset($this->_arGetVars[$strThisParameter])){
					$varActualValue = $this->_arGetVars[$strThisParameter];
				}
			};
			if ($intThisMethod == Constraint::$VERB_METHOD_POST) {
				if (isset($this->_arPostVars[$strThisParameter])){
					$varActualValue = $this->_arPostVars[$strThisParameter];
				}
			};
			$intConstraintType = $objThisConstraint->GetConstraintType();
			$strConstraintOperand = $objThisConstraint->GetConstraintOperand();

			$thisFail = false;
			$objFailureObject = new constraintfailure($strThisParameter, $intThisMethod, $objThisConstraint);
			if ($varActualValue == NULL && $blRequired){
				$thisFail = true;
			}elseif($varActualValue == NULL && !$blRequired){
				$thisFail = false;
				if ($default != ""){
					$this->_arRequestVars[$strThisParameter] = $default;
				}
			}else{
				switch ($intConstraintType) {
					case Constraint::$CT_MINLENGTH:
						if (strlen((string)$varActualValue) < (integer)$strConstraintOperand) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_MAXLENGTH:
						if (strlen((string)$varActualValue) > (integer)$strConstraintOperand) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_PERMITTEDCHARACTERS:
						for ($j=0; $j<=strlen($varActualValue)-1; $j++) {
							$thisChar = substr($varActualValue, $j, 1);
							if (strpos($strConstraintOperand, $thisChar) === false) {
								$thisFail = true;
							};
						};
						break;
					case Constraint::$CT_NONPERMITTEDCHARACTERS:
						for ($j=0; $j<=strlen($varActualValue)-1; $j++) {
							$thisChar = substr($varActualValue, $j, 1);
							if (!(strpos($strConstraintOperand, $thisChar) === false)) {
								$thisFail = true;
							};
						};
						break;
					case Constraint::$CT_LESSTHAN:
						if ($varActualValue >= $strConstraintOperand) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_MORETHAN:
						if ($varActualValue <= $strConstraintOperand) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_EQUALTO:
						if ($varActualValue != $strConstraintOperand) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_NOTEQUALTO:
						if ($varActualValue == $strConstraintOperand) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_MUSTMATCHREGEXP:
						if (!(preg_match($strConstraintOperand, $varActualValue))) {
							$thisFail = true;
						};
						break;
					case Constraint::$CT_MUSTNOTMATCHREGEXP:
						if (preg_match($strConstraintOperand, $varActualValue)) {
							$thisFail = true;
						};
						break;
				};
			}
			if ($thisFail) {
				$anyFail = true;
				$this->_arObjConstraintFailure[] = $objFailureObject;
			};
		};
		if ($anyFail) {
			if ($this->_blRedirectOnConstraintFailure) {
				$targetURL = $_ENV["HTTP_REFERER"];
				if (!$targetURL) {
					$targetURL = $this->_strConstraintFailureDefaultRedirectTargetURL;
				};
				if ($this->_strConstraintFailureRedirectTargetURL) {
					$targetURL = $this->_strConstraintFailureRedirectTargetURL;
				};
				if ($targetURL) {
					$objToSerialize = $this;
					$strSerialization = serialize($objToSerialize);
					$strResult = setcookie ("phprqcOriginalRequestObject", $strSerialization, time() + 3600, "/");
					header("Location: $targetURL");
					exit(0);
				};
			};
		};
		return(!($anyFail));  // Returns TRUE if all tests passed, otherwise returns FALSE
	}

	function GetConstraintFailures() {
		if (!$this->_blHasRunConstraintTests) {
			$this->TestConstraints();
		};
		return($this->_arObjConstraintFailure);
	}
}
?>
