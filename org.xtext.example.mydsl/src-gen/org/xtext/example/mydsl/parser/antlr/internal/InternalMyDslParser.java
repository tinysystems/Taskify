package org.xtext.example.mydsl.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.example.mydsl.services.MyDslGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalMyDslParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_DOUBLE", "RULE_STRING", "RULE_BOOLEAN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'+'", "'/'", "'*'", "'>='", "'<='", "'=='", "'!='", "'<'", "'>'", "'='", "'AND'", "'OR'", "'print'", "'break'", "'string'", "'integer'", "'boolean'", "'GLOBAL'", "'entry'", "'task'", "'{'", "'}'", "'if'", "'('", "')'", "'else'", "'for'", "';'", "'while'", "','", "'next'", "'end'", "'['", "']'"
    };
    public static final int RULE_BOOLEAN=8;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int RULE_ID=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=5;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int RULE_STRING=7;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__37=37;
    public static final int RULE_DOUBLE=6;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;

    // delegates
    // delegators


        public InternalMyDslParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalMyDslParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalMyDslParser.tokenNames; }
    public String getGrammarFileName() { return "InternalMyDsl.g"; }



     	private MyDslGrammarAccess grammarAccess;

        public InternalMyDslParser(TokenStream input, MyDslGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "InkApp";
       	}

       	@Override
       	protected MyDslGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleInkApp"
    // InternalMyDsl.g:64:1: entryRuleInkApp returns [EObject current=null] : iv_ruleInkApp= ruleInkApp EOF ;
    public final EObject entryRuleInkApp() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInkApp = null;


        try {
            // InternalMyDsl.g:64:47: (iv_ruleInkApp= ruleInkApp EOF )
            // InternalMyDsl.g:65:2: iv_ruleInkApp= ruleInkApp EOF
            {
             newCompositeNode(grammarAccess.getInkAppRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleInkApp=ruleInkApp();

            state._fsp--;

             current =iv_ruleInkApp; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleInkApp"


    // $ANTLR start "ruleInkApp"
    // InternalMyDsl.g:71:1: ruleInkApp returns [EObject current=null] : ( ( (lv_globals_0_0= ruleGlobalVariableExpression ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) ) ;
    public final EObject ruleInkApp() throws RecognitionException {
        EObject current = null;

        EObject lv_globals_0_0 = null;

        EObject lv_tasks_1_0 = null;

        EObject lv_entry_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:77:2: ( ( ( (lv_globals_0_0= ruleGlobalVariableExpression ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) ) )
            // InternalMyDsl.g:78:2: ( ( (lv_globals_0_0= ruleGlobalVariableExpression ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) )
            {
            // InternalMyDsl.g:78:2: ( ( (lv_globals_0_0= ruleGlobalVariableExpression ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) )
            // InternalMyDsl.g:79:3: ( (lv_globals_0_0= ruleGlobalVariableExpression ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) )
            {
            // InternalMyDsl.g:79:3: ( (lv_globals_0_0= ruleGlobalVariableExpression ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==31) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:80:4: (lv_globals_0_0= ruleGlobalVariableExpression )
            	    {
            	    // InternalMyDsl.g:80:4: (lv_globals_0_0= ruleGlobalVariableExpression )
            	    // InternalMyDsl.g:81:5: lv_globals_0_0= ruleGlobalVariableExpression
            	    {

            	    					newCompositeNode(grammarAccess.getInkAppAccess().getGlobalsGlobalVariableExpressionParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_globals_0_0=ruleGlobalVariableExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getInkAppRule());
            	    					}
            	    					add(
            	    						current,
            	    						"globals",
            	    						lv_globals_0_0,
            	    						"org.xtext.example.mydsl.MyDsl.GlobalVariableExpression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // InternalMyDsl.g:98:3: ( (lv_tasks_1_0= ruleTask ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==33) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalMyDsl.g:99:4: (lv_tasks_1_0= ruleTask )
            	    {
            	    // InternalMyDsl.g:99:4: (lv_tasks_1_0= ruleTask )
            	    // InternalMyDsl.g:100:5: lv_tasks_1_0= ruleTask
            	    {

            	    					newCompositeNode(grammarAccess.getInkAppAccess().getTasksTaskParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_tasks_1_0=ruleTask();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getInkAppRule());
            	    					}
            	    					add(
            	    						current,
            	    						"tasks",
            	    						lv_tasks_1_0,
            	    						"org.xtext.example.mydsl.MyDsl.Task");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // InternalMyDsl.g:117:3: ( (lv_entry_2_0= ruleEntryTask ) )
            // InternalMyDsl.g:118:4: (lv_entry_2_0= ruleEntryTask )
            {
            // InternalMyDsl.g:118:4: (lv_entry_2_0= ruleEntryTask )
            // InternalMyDsl.g:119:5: lv_entry_2_0= ruleEntryTask
            {

            					newCompositeNode(grammarAccess.getInkAppAccess().getEntryEntryTaskParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_entry_2_0=ruleEntryTask();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getInkAppRule());
            					}
            					set(
            						current,
            						"entry",
            						lv_entry_2_0,
            						"org.xtext.example.mydsl.MyDsl.EntryTask");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleInkApp"


    // $ANTLR start "entryRuleOperator"
    // InternalMyDsl.g:140:1: entryRuleOperator returns [String current=null] : iv_ruleOperator= ruleOperator EOF ;
    public final String entryRuleOperator() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleOperator = null;


        try {
            // InternalMyDsl.g:140:48: (iv_ruleOperator= ruleOperator EOF )
            // InternalMyDsl.g:141:2: iv_ruleOperator= ruleOperator EOF
            {
             newCompositeNode(grammarAccess.getOperatorRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperator=ruleOperator();

            state._fsp--;

             current =iv_ruleOperator.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperator"


    // $ANTLR start "ruleOperator"
    // InternalMyDsl.g:147:1: ruleOperator returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '-' | kw= '+' | kw= '/' | kw= '*' | kw= '>=' | kw= '<=' | kw= '==' | kw= '!=' | kw= '<' | kw= '>' | kw= '=' | kw= 'AND' | kw= 'OR' ) ;
    public final AntlrDatatypeRuleToken ruleOperator() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalMyDsl.g:153:2: ( (kw= '-' | kw= '+' | kw= '/' | kw= '*' | kw= '>=' | kw= '<=' | kw= '==' | kw= '!=' | kw= '<' | kw= '>' | kw= '=' | kw= 'AND' | kw= 'OR' ) )
            // InternalMyDsl.g:154:2: (kw= '-' | kw= '+' | kw= '/' | kw= '*' | kw= '>=' | kw= '<=' | kw= '==' | kw= '!=' | kw= '<' | kw= '>' | kw= '=' | kw= 'AND' | kw= 'OR' )
            {
            // InternalMyDsl.g:154:2: (kw= '-' | kw= '+' | kw= '/' | kw= '*' | kw= '>=' | kw= '<=' | kw= '==' | kw= '!=' | kw= '<' | kw= '>' | kw= '=' | kw= 'AND' | kw= 'OR' )
            int alt3=13;
            switch ( input.LA(1) ) {
            case 13:
                {
                alt3=1;
                }
                break;
            case 14:
                {
                alt3=2;
                }
                break;
            case 15:
                {
                alt3=3;
                }
                break;
            case 16:
                {
                alt3=4;
                }
                break;
            case 17:
                {
                alt3=5;
                }
                break;
            case 18:
                {
                alt3=6;
                }
                break;
            case 19:
                {
                alt3=7;
                }
                break;
            case 20:
                {
                alt3=8;
                }
                break;
            case 21:
                {
                alt3=9;
                }
                break;
            case 22:
                {
                alt3=10;
                }
                break;
            case 23:
                {
                alt3=11;
                }
                break;
            case 24:
                {
                alt3=12;
                }
                break;
            case 25:
                {
                alt3=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalMyDsl.g:155:3: kw= '-'
                    {
                    kw=(Token)match(input,13,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getHyphenMinusKeyword_0());
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:161:3: kw= '+'
                    {
                    kw=(Token)match(input,14,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getPlusSignKeyword_1());
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:167:3: kw= '/'
                    {
                    kw=(Token)match(input,15,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getSolidusKeyword_2());
                    		

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:173:3: kw= '*'
                    {
                    kw=(Token)match(input,16,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getAsteriskKeyword_3());
                    		

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:179:3: kw= '>='
                    {
                    kw=(Token)match(input,17,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getGreaterThanSignEqualsSignKeyword_4());
                    		

                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:185:3: kw= '<='
                    {
                    kw=(Token)match(input,18,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getLessThanSignEqualsSignKeyword_5());
                    		

                    }
                    break;
                case 7 :
                    // InternalMyDsl.g:191:3: kw= '=='
                    {
                    kw=(Token)match(input,19,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getEqualsSignEqualsSignKeyword_6());
                    		

                    }
                    break;
                case 8 :
                    // InternalMyDsl.g:197:3: kw= '!='
                    {
                    kw=(Token)match(input,20,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getExclamationMarkEqualsSignKeyword_7());
                    		

                    }
                    break;
                case 9 :
                    // InternalMyDsl.g:203:3: kw= '<'
                    {
                    kw=(Token)match(input,21,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getLessThanSignKeyword_8());
                    		

                    }
                    break;
                case 10 :
                    // InternalMyDsl.g:209:3: kw= '>'
                    {
                    kw=(Token)match(input,22,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getGreaterThanSignKeyword_9());
                    		

                    }
                    break;
                case 11 :
                    // InternalMyDsl.g:215:3: kw= '='
                    {
                    kw=(Token)match(input,23,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getEqualsSignKeyword_10());
                    		

                    }
                    break;
                case 12 :
                    // InternalMyDsl.g:221:3: kw= 'AND'
                    {
                    kw=(Token)match(input,24,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getANDKeyword_11());
                    		

                    }
                    break;
                case 13 :
                    // InternalMyDsl.g:227:3: kw= 'OR'
                    {
                    kw=(Token)match(input,25,FOLLOW_2); 

                    			current.merge(kw);
                    			newLeafNode(kw, grammarAccess.getOperatorAccess().getORKeyword_12());
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperator"


    // $ANTLR start "entryRuleBuiltinFunctions"
    // InternalMyDsl.g:236:1: entryRuleBuiltinFunctions returns [String current=null] : iv_ruleBuiltinFunctions= ruleBuiltinFunctions EOF ;
    public final String entryRuleBuiltinFunctions() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleBuiltinFunctions = null;


        try {
            // InternalMyDsl.g:236:56: (iv_ruleBuiltinFunctions= ruleBuiltinFunctions EOF )
            // InternalMyDsl.g:237:2: iv_ruleBuiltinFunctions= ruleBuiltinFunctions EOF
            {
             newCompositeNode(grammarAccess.getBuiltinFunctionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBuiltinFunctions=ruleBuiltinFunctions();

            state._fsp--;

             current =iv_ruleBuiltinFunctions.getText(); 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBuiltinFunctions"


    // $ANTLR start "ruleBuiltinFunctions"
    // InternalMyDsl.g:243:1: ruleBuiltinFunctions returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : kw= 'print' ;
    public final AntlrDatatypeRuleToken ruleBuiltinFunctions() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;


        	enterRule();

        try {
            // InternalMyDsl.g:249:2: (kw= 'print' )
            // InternalMyDsl.g:250:2: kw= 'print'
            {
            kw=(Token)match(input,26,FOLLOW_2); 

            		current.merge(kw);
            		newLeafNode(kw, grammarAccess.getBuiltinFunctionsAccess().getPrintKeyword());
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBuiltinFunctions"


    // $ANTLR start "entryRuleBreakExpression"
    // InternalMyDsl.g:258:1: entryRuleBreakExpression returns [EObject current=null] : iv_ruleBreakExpression= ruleBreakExpression EOF ;
    public final EObject entryRuleBreakExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBreakExpression = null;


        try {
            // InternalMyDsl.g:258:56: (iv_ruleBreakExpression= ruleBreakExpression EOF )
            // InternalMyDsl.g:259:2: iv_ruleBreakExpression= ruleBreakExpression EOF
            {
             newCompositeNode(grammarAccess.getBreakExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBreakExpression=ruleBreakExpression();

            state._fsp--;

             current =iv_ruleBreakExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBreakExpression"


    // $ANTLR start "ruleBreakExpression"
    // InternalMyDsl.g:265:1: ruleBreakExpression returns [EObject current=null] : ( () ( (lv_break_1_0= 'break' ) ) ) ;
    public final EObject ruleBreakExpression() throws RecognitionException {
        EObject current = null;

        Token lv_break_1_0=null;


        	enterRule();

        try {
            // InternalMyDsl.g:271:2: ( ( () ( (lv_break_1_0= 'break' ) ) ) )
            // InternalMyDsl.g:272:2: ( () ( (lv_break_1_0= 'break' ) ) )
            {
            // InternalMyDsl.g:272:2: ( () ( (lv_break_1_0= 'break' ) ) )
            // InternalMyDsl.g:273:3: () ( (lv_break_1_0= 'break' ) )
            {
            // InternalMyDsl.g:273:3: ()
            // InternalMyDsl.g:274:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBreakExpressionAccess().getBreakExpressionAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:280:3: ( (lv_break_1_0= 'break' ) )
            // InternalMyDsl.g:281:4: (lv_break_1_0= 'break' )
            {
            // InternalMyDsl.g:281:4: (lv_break_1_0= 'break' )
            // InternalMyDsl.g:282:5: lv_break_1_0= 'break'
            {
            lv_break_1_0=(Token)match(input,27,FOLLOW_2); 

            					newLeafNode(lv_break_1_0, grammarAccess.getBreakExpressionAccess().getBreakBreakKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBreakExpressionRule());
            					}
            					setWithLastConsumed(current, "break", lv_break_1_0, "break");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBreakExpression"


    // $ANTLR start "entryRuleVariableType"
    // InternalMyDsl.g:298:1: entryRuleVariableType returns [EObject current=null] : iv_ruleVariableType= ruleVariableType EOF ;
    public final EObject entryRuleVariableType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableType = null;


        try {
            // InternalMyDsl.g:298:53: (iv_ruleVariableType= ruleVariableType EOF )
            // InternalMyDsl.g:299:2: iv_ruleVariableType= ruleVariableType EOF
            {
             newCompositeNode(grammarAccess.getVariableTypeRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableType=ruleVariableType();

            state._fsp--;

             current =iv_ruleVariableType; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableType"


    // $ANTLR start "ruleVariableType"
    // InternalMyDsl.g:305:1: ruleVariableType returns [EObject current=null] : ( () ( ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) ) ) ) ;
    public final EObject ruleVariableType() throws RecognitionException {
        EObject current = null;

        Token lv_type_1_1=null;
        Token lv_type_1_2=null;
        Token lv_type_1_3=null;


        	enterRule();

        try {
            // InternalMyDsl.g:311:2: ( ( () ( ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) ) ) ) )
            // InternalMyDsl.g:312:2: ( () ( ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) ) ) )
            {
            // InternalMyDsl.g:312:2: ( () ( ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) ) ) )
            // InternalMyDsl.g:313:3: () ( ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) ) )
            {
            // InternalMyDsl.g:313:3: ()
            // InternalMyDsl.g:314:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getVariableTypeAccess().getVariableTypeAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:320:3: ( ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) ) )
            // InternalMyDsl.g:321:4: ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) )
            {
            // InternalMyDsl.g:321:4: ( (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' ) )
            // InternalMyDsl.g:322:5: (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' )
            {
            // InternalMyDsl.g:322:5: (lv_type_1_1= 'string' | lv_type_1_2= 'integer' | lv_type_1_3= 'boolean' )
            int alt4=3;
            switch ( input.LA(1) ) {
            case 28:
                {
                alt4=1;
                }
                break;
            case 29:
                {
                alt4=2;
                }
                break;
            case 30:
                {
                alt4=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;
            }

            switch (alt4) {
                case 1 :
                    // InternalMyDsl.g:323:6: lv_type_1_1= 'string'
                    {
                    lv_type_1_1=(Token)match(input,28,FOLLOW_2); 

                    						newLeafNode(lv_type_1_1, grammarAccess.getVariableTypeAccess().getTypeStringKeyword_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableTypeRule());
                    						}
                    						setWithLastConsumed(current, "type", lv_type_1_1, null);
                    					

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:334:6: lv_type_1_2= 'integer'
                    {
                    lv_type_1_2=(Token)match(input,29,FOLLOW_2); 

                    						newLeafNode(lv_type_1_2, grammarAccess.getVariableTypeAccess().getTypeIntegerKeyword_1_0_1());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableTypeRule());
                    						}
                    						setWithLastConsumed(current, "type", lv_type_1_2, null);
                    					

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:345:6: lv_type_1_3= 'boolean'
                    {
                    lv_type_1_3=(Token)match(input,30,FOLLOW_2); 

                    						newLeafNode(lv_type_1_3, grammarAccess.getVariableTypeAccess().getTypeBooleanKeyword_1_0_2());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableTypeRule());
                    						}
                    						setWithLastConsumed(current, "type", lv_type_1_3, null);
                    					

                    }
                    break;

            }


            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableType"


    // $ANTLR start "entryRuleGlobalVariableExpression"
    // InternalMyDsl.g:362:1: entryRuleGlobalVariableExpression returns [EObject current=null] : iv_ruleGlobalVariableExpression= ruleGlobalVariableExpression EOF ;
    public final EObject entryRuleGlobalVariableExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVariableExpression = null;


        try {
            // InternalMyDsl.g:362:65: (iv_ruleGlobalVariableExpression= ruleGlobalVariableExpression EOF )
            // InternalMyDsl.g:363:2: iv_ruleGlobalVariableExpression= ruleGlobalVariableExpression EOF
            {
             newCompositeNode(grammarAccess.getGlobalVariableExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalVariableExpression=ruleGlobalVariableExpression();

            state._fsp--;

             current =iv_ruleGlobalVariableExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleGlobalVariableExpression"


    // $ANTLR start "ruleGlobalVariableExpression"
    // InternalMyDsl.g:369:1: ruleGlobalVariableExpression returns [EObject current=null] : ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDeclerationExpression ) ) ) ;
    public final EObject ruleGlobalVariableExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_variable_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:375:2: ( ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDeclerationExpression ) ) ) )
            // InternalMyDsl.g:376:2: ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDeclerationExpression ) ) )
            {
            // InternalMyDsl.g:376:2: ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDeclerationExpression ) ) )
            // InternalMyDsl.g:377:3: () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDeclerationExpression ) )
            {
            // InternalMyDsl.g:377:3: ()
            // InternalMyDsl.g:378:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getGlobalVariableExpressionAccess().getGlobalVariableExpressionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,31,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getGlobalVariableExpressionAccess().getGLOBALKeyword_1());
            		
            // InternalMyDsl.g:388:3: ( (lv_variable_2_0= ruleVariableDeclerationExpression ) )
            // InternalMyDsl.g:389:4: (lv_variable_2_0= ruleVariableDeclerationExpression )
            {
            // InternalMyDsl.g:389:4: (lv_variable_2_0= ruleVariableDeclerationExpression )
            // InternalMyDsl.g:390:5: lv_variable_2_0= ruleVariableDeclerationExpression
            {

            					newCompositeNode(grammarAccess.getGlobalVariableExpressionAccess().getVariableVariableDeclerationExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_variable_2_0=ruleVariableDeclerationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGlobalVariableExpressionRule());
            					}
            					set(
            						current,
            						"variable",
            						lv_variable_2_0,
            						"org.xtext.example.mydsl.MyDsl.VariableDeclerationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleGlobalVariableExpression"


    // $ANTLR start "entryRuleEntryTask"
    // InternalMyDsl.g:411:1: entryRuleEntryTask returns [EObject current=null] : iv_ruleEntryTask= ruleEntryTask EOF ;
    public final EObject entryRuleEntryTask() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntryTask = null;


        try {
            // InternalMyDsl.g:411:50: (iv_ruleEntryTask= ruleEntryTask EOF )
            // InternalMyDsl.g:412:2: iv_ruleEntryTask= ruleEntryTask EOF
            {
             newCompositeNode(grammarAccess.getEntryTaskRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleEntryTask=ruleEntryTask();

            state._fsp--;

             current =iv_ruleEntryTask; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleEntryTask"


    // $ANTLR start "ruleEntryTask"
    // InternalMyDsl.g:418:1: ruleEntryTask returns [EObject current=null] : (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) ) ;
    public final EObject ruleEntryTask() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_task_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:424:2: ( (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) ) )
            // InternalMyDsl.g:425:2: (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) )
            {
            // InternalMyDsl.g:425:2: (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) )
            // InternalMyDsl.g:426:3: otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) )
            {
            otherlv_0=(Token)match(input,32,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getEntryTaskAccess().getEntryKeyword_0());
            		
            // InternalMyDsl.g:430:3: ( (lv_task_1_0= ruleTask ) )
            // InternalMyDsl.g:431:4: (lv_task_1_0= ruleTask )
            {
            // InternalMyDsl.g:431:4: (lv_task_1_0= ruleTask )
            // InternalMyDsl.g:432:5: lv_task_1_0= ruleTask
            {

            					newCompositeNode(grammarAccess.getEntryTaskAccess().getTaskTaskParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_2);
            lv_task_1_0=ruleTask();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getEntryTaskRule());
            					}
            					set(
            						current,
            						"task",
            						lv_task_1_0,
            						"org.xtext.example.mydsl.MyDsl.Task");
            					afterParserOrEnumRuleCall();
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleEntryTask"


    // $ANTLR start "entryRuleTask"
    // InternalMyDsl.g:453:1: entryRuleTask returns [EObject current=null] : iv_ruleTask= ruleTask EOF ;
    public final EObject entryRuleTask() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTask = null;


        try {
            // InternalMyDsl.g:453:45: (iv_ruleTask= ruleTask EOF )
            // InternalMyDsl.g:454:2: iv_ruleTask= ruleTask EOF
            {
             newCompositeNode(grammarAccess.getTaskRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTask=ruleTask();

            state._fsp--;

             current =iv_ruleTask; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTask"


    // $ANTLR start "ruleTask"
    // InternalMyDsl.g:460:1: ruleTask returns [EObject current=null] : ( () otherlv_1= 'task' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_taskbody_4_0= ruleTaskBody ) ) otherlv_5= '}' ) ;
    public final EObject ruleTask() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_taskbody_4_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:466:2: ( ( () otherlv_1= 'task' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_taskbody_4_0= ruleTaskBody ) ) otherlv_5= '}' ) )
            // InternalMyDsl.g:467:2: ( () otherlv_1= 'task' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_taskbody_4_0= ruleTaskBody ) ) otherlv_5= '}' )
            {
            // InternalMyDsl.g:467:2: ( () otherlv_1= 'task' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_taskbody_4_0= ruleTaskBody ) ) otherlv_5= '}' )
            // InternalMyDsl.g:468:3: () otherlv_1= 'task' ( (lv_name_2_0= RULE_ID ) ) otherlv_3= '{' ( (lv_taskbody_4_0= ruleTaskBody ) ) otherlv_5= '}'
            {
            // InternalMyDsl.g:468:3: ()
            // InternalMyDsl.g:469:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTaskAccess().getTaskAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,33,FOLLOW_7); 

            			newLeafNode(otherlv_1, grammarAccess.getTaskAccess().getTaskKeyword_1());
            		
            // InternalMyDsl.g:479:3: ( (lv_name_2_0= RULE_ID ) )
            // InternalMyDsl.g:480:4: (lv_name_2_0= RULE_ID )
            {
            // InternalMyDsl.g:480:4: (lv_name_2_0= RULE_ID )
            // InternalMyDsl.g:481:5: lv_name_2_0= RULE_ID
            {
            lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_name_2_0, grammarAccess.getTaskAccess().getNameIDTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTaskRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_3=(Token)match(input,34,FOLLOW_9); 

            			newLeafNode(otherlv_3, grammarAccess.getTaskAccess().getLeftCurlyBracketKeyword_3());
            		
            // InternalMyDsl.g:501:3: ( (lv_taskbody_4_0= ruleTaskBody ) )
            // InternalMyDsl.g:502:4: (lv_taskbody_4_0= ruleTaskBody )
            {
            // InternalMyDsl.g:502:4: (lv_taskbody_4_0= ruleTaskBody )
            // InternalMyDsl.g:503:5: lv_taskbody_4_0= ruleTaskBody
            {

            					newCompositeNode(grammarAccess.getTaskAccess().getTaskbodyTaskBodyParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_10);
            lv_taskbody_4_0=ruleTaskBody();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getTaskRule());
            					}
            					set(
            						current,
            						"taskbody",
            						lv_taskbody_4_0,
            						"org.xtext.example.mydsl.MyDsl.TaskBody");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,35,FOLLOW_2); 

            			newLeafNode(otherlv_5, grammarAccess.getTaskAccess().getRightCurlyBracketKeyword_5());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTask"


    // $ANTLR start "entryRuleIfExpression"
    // InternalMyDsl.g:528:1: entryRuleIfExpression returns [EObject current=null] : iv_ruleIfExpression= ruleIfExpression EOF ;
    public final EObject entryRuleIfExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpression = null;


        try {
            // InternalMyDsl.g:528:53: (iv_ruleIfExpression= ruleIfExpression EOF )
            // InternalMyDsl.g:529:2: iv_ruleIfExpression= ruleIfExpression EOF
            {
             newCompositeNode(grammarAccess.getIfExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIfExpression=ruleIfExpression();

            state._fsp--;

             current =iv_ruleIfExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIfExpression"


    // $ANTLR start "ruleIfExpression"
    // InternalMyDsl.g:535:1: ruleIfExpression returns [EObject current=null] : ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_ifcondition_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_ifbody_6_0= ruleStatementBody ) ) otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}' )* (otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}' )? ) ;
    public final EObject ruleIfExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_15=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        EObject lv_ifcondition_3_0 = null;

        EObject lv_ifbody_6_0 = null;

        EObject lv_elseifcondition_11_0 = null;

        EObject lv_elseifbody_14_0 = null;

        EObject lv_elsebody_18_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:541:2: ( ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_ifcondition_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_ifbody_6_0= ruleStatementBody ) ) otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}' )* (otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}' )? ) )
            // InternalMyDsl.g:542:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_ifcondition_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_ifbody_6_0= ruleStatementBody ) ) otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}' )* (otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}' )? )
            {
            // InternalMyDsl.g:542:2: ( () otherlv_1= 'if' otherlv_2= '(' ( (lv_ifcondition_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_ifbody_6_0= ruleStatementBody ) ) otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}' )* (otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}' )? )
            // InternalMyDsl.g:543:3: () otherlv_1= 'if' otherlv_2= '(' ( (lv_ifcondition_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_ifbody_6_0= ruleStatementBody ) ) otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}' )* (otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}' )?
            {
            // InternalMyDsl.g:543:3: ()
            // InternalMyDsl.g:544:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getIfExpressionAccess().getIfExpressionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,36,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getIfExpressionAccess().getIfKeyword_1());
            		
            otherlv_2=(Token)match(input,37,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getIfExpressionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:558:3: ( (lv_ifcondition_3_0= ruleOperationExpression ) )
            // InternalMyDsl.g:559:4: (lv_ifcondition_3_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:559:4: (lv_ifcondition_3_0= ruleOperationExpression )
            // InternalMyDsl.g:560:5: lv_ifcondition_3_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getIfExpressionAccess().getIfconditionOperationExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_ifcondition_3_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            					}
            					set(
            						current,
            						"ifcondition",
            						lv_ifcondition_3_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,38,FOLLOW_8); 

            			newLeafNode(otherlv_4, grammarAccess.getIfExpressionAccess().getRightParenthesisKeyword_4());
            		
            otherlv_5=(Token)match(input,34,FOLLOW_14); 

            			newLeafNode(otherlv_5, grammarAccess.getIfExpressionAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalMyDsl.g:585:3: ( (lv_ifbody_6_0= ruleStatementBody ) )
            // InternalMyDsl.g:586:4: (lv_ifbody_6_0= ruleStatementBody )
            {
            // InternalMyDsl.g:586:4: (lv_ifbody_6_0= ruleStatementBody )
            // InternalMyDsl.g:587:5: lv_ifbody_6_0= ruleStatementBody
            {

            					newCompositeNode(grammarAccess.getIfExpressionAccess().getIfbodyStatementBodyParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_10);
            lv_ifbody_6_0=ruleStatementBody();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            					}
            					set(
            						current,
            						"ifbody",
            						lv_ifbody_6_0,
            						"org.xtext.example.mydsl.MyDsl.StatementBody");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,35,FOLLOW_15); 

            			newLeafNode(otherlv_7, grammarAccess.getIfExpressionAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalMyDsl.g:608:3: (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==39) ) {
                    int LA5_1 = input.LA(2);

                    if ( (LA5_1==36) ) {
                        alt5=1;
                    }


                }


                switch (alt5) {
            	case 1 :
            	    // InternalMyDsl.g:609:4: otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elseifcondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleStatementBody ) ) otherlv_15= '}'
            	    {
            	    otherlv_8=(Token)match(input,39,FOLLOW_16); 

            	    				newLeafNode(otherlv_8, grammarAccess.getIfExpressionAccess().getElseKeyword_8_0());
            	    			
            	    otherlv_9=(Token)match(input,36,FOLLOW_11); 

            	    				newLeafNode(otherlv_9, grammarAccess.getIfExpressionAccess().getIfKeyword_8_1());
            	    			
            	    otherlv_10=(Token)match(input,37,FOLLOW_12); 

            	    				newLeafNode(otherlv_10, grammarAccess.getIfExpressionAccess().getLeftParenthesisKeyword_8_2());
            	    			
            	    // InternalMyDsl.g:621:4: ( (lv_elseifcondition_11_0= ruleOperationExpression ) )
            	    // InternalMyDsl.g:622:5: (lv_elseifcondition_11_0= ruleOperationExpression )
            	    {
            	    // InternalMyDsl.g:622:5: (lv_elseifcondition_11_0= ruleOperationExpression )
            	    // InternalMyDsl.g:623:6: lv_elseifcondition_11_0= ruleOperationExpression
            	    {

            	    						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseifconditionOperationExpressionParserRuleCall_8_3_0());
            	    					
            	    pushFollow(FOLLOW_13);
            	    lv_elseifcondition_11_0=ruleOperationExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elseifcondition",
            	    							lv_elseifcondition_11_0,
            	    							"org.xtext.example.mydsl.MyDsl.OperationExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_12=(Token)match(input,38,FOLLOW_8); 

            	    				newLeafNode(otherlv_12, grammarAccess.getIfExpressionAccess().getRightParenthesisKeyword_8_4());
            	    			
            	    otherlv_13=(Token)match(input,34,FOLLOW_14); 

            	    				newLeafNode(otherlv_13, grammarAccess.getIfExpressionAccess().getLeftCurlyBracketKeyword_8_5());
            	    			
            	    // InternalMyDsl.g:648:4: ( (lv_elseifbody_14_0= ruleStatementBody ) )
            	    // InternalMyDsl.g:649:5: (lv_elseifbody_14_0= ruleStatementBody )
            	    {
            	    // InternalMyDsl.g:649:5: (lv_elseifbody_14_0= ruleStatementBody )
            	    // InternalMyDsl.g:650:6: lv_elseifbody_14_0= ruleStatementBody
            	    {

            	    						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseifbodyStatementBodyParserRuleCall_8_6_0());
            	    					
            	    pushFollow(FOLLOW_10);
            	    lv_elseifbody_14_0=ruleStatementBody();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elseifbody",
            	    							lv_elseifbody_14_0,
            	    							"org.xtext.example.mydsl.MyDsl.StatementBody");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_15=(Token)match(input,35,FOLLOW_15); 

            	    				newLeafNode(otherlv_15, grammarAccess.getIfExpressionAccess().getRightCurlyBracketKeyword_8_7());
            	    			

            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalMyDsl.g:672:3: (otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==39) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // InternalMyDsl.g:673:4: otherlv_16= 'else' otherlv_17= '{' ( (lv_elsebody_18_0= ruleStatementBody ) ) otherlv_19= '}'
                    {
                    otherlv_16=(Token)match(input,39,FOLLOW_8); 

                    				newLeafNode(otherlv_16, grammarAccess.getIfExpressionAccess().getElseKeyword_9_0());
                    			
                    otherlv_17=(Token)match(input,34,FOLLOW_14); 

                    				newLeafNode(otherlv_17, grammarAccess.getIfExpressionAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalMyDsl.g:681:4: ( (lv_elsebody_18_0= ruleStatementBody ) )
                    // InternalMyDsl.g:682:5: (lv_elsebody_18_0= ruleStatementBody )
                    {
                    // InternalMyDsl.g:682:5: (lv_elsebody_18_0= ruleStatementBody )
                    // InternalMyDsl.g:683:6: lv_elsebody_18_0= ruleStatementBody
                    {

                    						newCompositeNode(grammarAccess.getIfExpressionAccess().getElsebodyStatementBodyParserRuleCall_9_2_0());
                    					
                    pushFollow(FOLLOW_10);
                    lv_elsebody_18_0=ruleStatementBody();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
                    						}
                    						set(
                    							current,
                    							"elsebody",
                    							lv_elsebody_18_0,
                    							"org.xtext.example.mydsl.MyDsl.StatementBody");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    otherlv_19=(Token)match(input,35,FOLLOW_2); 

                    				newLeafNode(otherlv_19, grammarAccess.getIfExpressionAccess().getRightCurlyBracketKeyword_9_3());
                    			

                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIfExpression"


    // $ANTLR start "entryRuleForExpression"
    // InternalMyDsl.g:709:1: entryRuleForExpression returns [EObject current=null] : iv_ruleForExpression= ruleForExpression EOF ;
    public final EObject entryRuleForExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForExpression = null;


        try {
            // InternalMyDsl.g:709:54: (iv_ruleForExpression= ruleForExpression EOF )
            // InternalMyDsl.g:710:2: iv_ruleForExpression= ruleForExpression EOF
            {
             newCompositeNode(grammarAccess.getForExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleForExpression=ruleForExpression();

            state._fsp--;

             current =iv_ruleForExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleForExpression"


    // $ANTLR start "ruleForExpression"
    // InternalMyDsl.g:716:1: ruleForExpression returns [EObject current=null] : ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initial_3_0= ruleOperationExpression ) ) otherlv_4= ';' ( (lv_test_5_0= ruleOperationExpression ) ) otherlv_6= ';' ( (lv_update_7_0= ruleOperationExpression ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_body_10_0= ruleStatementBody ) ) otherlv_11= '}' ) ;
    public final EObject ruleForExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        EObject lv_initial_3_0 = null;

        EObject lv_test_5_0 = null;

        EObject lv_update_7_0 = null;

        EObject lv_body_10_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:722:2: ( ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initial_3_0= ruleOperationExpression ) ) otherlv_4= ';' ( (lv_test_5_0= ruleOperationExpression ) ) otherlv_6= ';' ( (lv_update_7_0= ruleOperationExpression ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_body_10_0= ruleStatementBody ) ) otherlv_11= '}' ) )
            // InternalMyDsl.g:723:2: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initial_3_0= ruleOperationExpression ) ) otherlv_4= ';' ( (lv_test_5_0= ruleOperationExpression ) ) otherlv_6= ';' ( (lv_update_7_0= ruleOperationExpression ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_body_10_0= ruleStatementBody ) ) otherlv_11= '}' )
            {
            // InternalMyDsl.g:723:2: ( () otherlv_1= 'for' otherlv_2= '(' ( (lv_initial_3_0= ruleOperationExpression ) ) otherlv_4= ';' ( (lv_test_5_0= ruleOperationExpression ) ) otherlv_6= ';' ( (lv_update_7_0= ruleOperationExpression ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_body_10_0= ruleStatementBody ) ) otherlv_11= '}' )
            // InternalMyDsl.g:724:3: () otherlv_1= 'for' otherlv_2= '(' ( (lv_initial_3_0= ruleOperationExpression ) ) otherlv_4= ';' ( (lv_test_5_0= ruleOperationExpression ) ) otherlv_6= ';' ( (lv_update_7_0= ruleOperationExpression ) ) otherlv_8= ')' otherlv_9= '{' ( (lv_body_10_0= ruleStatementBody ) ) otherlv_11= '}'
            {
            // InternalMyDsl.g:724:3: ()
            // InternalMyDsl.g:725:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getForExpressionAccess().getForExpressionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,40,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getForExpressionAccess().getForKeyword_1());
            		
            otherlv_2=(Token)match(input,37,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getForExpressionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:739:3: ( (lv_initial_3_0= ruleOperationExpression ) )
            // InternalMyDsl.g:740:4: (lv_initial_3_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:740:4: (lv_initial_3_0= ruleOperationExpression )
            // InternalMyDsl.g:741:5: lv_initial_3_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getInitialOperationExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_17);
            lv_initial_3_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"initial",
            						lv_initial_3_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,41,FOLLOW_12); 

            			newLeafNode(otherlv_4, grammarAccess.getForExpressionAccess().getSemicolonKeyword_4());
            		
            // InternalMyDsl.g:762:3: ( (lv_test_5_0= ruleOperationExpression ) )
            // InternalMyDsl.g:763:4: (lv_test_5_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:763:4: (lv_test_5_0= ruleOperationExpression )
            // InternalMyDsl.g:764:5: lv_test_5_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getTestOperationExpressionParserRuleCall_5_0());
            				
            pushFollow(FOLLOW_17);
            lv_test_5_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"test",
            						lv_test_5_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_6=(Token)match(input,41,FOLLOW_12); 

            			newLeafNode(otherlv_6, grammarAccess.getForExpressionAccess().getSemicolonKeyword_6());
            		
            // InternalMyDsl.g:785:3: ( (lv_update_7_0= ruleOperationExpression ) )
            // InternalMyDsl.g:786:4: (lv_update_7_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:786:4: (lv_update_7_0= ruleOperationExpression )
            // InternalMyDsl.g:787:5: lv_update_7_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getUpdateOperationExpressionParserRuleCall_7_0());
            				
            pushFollow(FOLLOW_13);
            lv_update_7_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"update",
            						lv_update_7_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_8=(Token)match(input,38,FOLLOW_8); 

            			newLeafNode(otherlv_8, grammarAccess.getForExpressionAccess().getRightParenthesisKeyword_8());
            		
            otherlv_9=(Token)match(input,34,FOLLOW_14); 

            			newLeafNode(otherlv_9, grammarAccess.getForExpressionAccess().getLeftCurlyBracketKeyword_9());
            		
            // InternalMyDsl.g:812:3: ( (lv_body_10_0= ruleStatementBody ) )
            // InternalMyDsl.g:813:4: (lv_body_10_0= ruleStatementBody )
            {
            // InternalMyDsl.g:813:4: (lv_body_10_0= ruleStatementBody )
            // InternalMyDsl.g:814:5: lv_body_10_0= ruleStatementBody
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getBodyStatementBodyParserRuleCall_10_0());
            				
            pushFollow(FOLLOW_10);
            lv_body_10_0=ruleStatementBody();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_10_0,
            						"org.xtext.example.mydsl.MyDsl.StatementBody");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_11=(Token)match(input,35,FOLLOW_2); 

            			newLeafNode(otherlv_11, grammarAccess.getForExpressionAccess().getRightCurlyBracketKeyword_11());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleForExpression"


    // $ANTLR start "entryRuleWhileExpression"
    // InternalMyDsl.g:839:1: entryRuleWhileExpression returns [EObject current=null] : iv_ruleWhileExpression= ruleWhileExpression EOF ;
    public final EObject entryRuleWhileExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileExpression = null;


        try {
            // InternalMyDsl.g:839:56: (iv_ruleWhileExpression= ruleWhileExpression EOF )
            // InternalMyDsl.g:840:2: iv_ruleWhileExpression= ruleWhileExpression EOF
            {
             newCompositeNode(grammarAccess.getWhileExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleWhileExpression=ruleWhileExpression();

            state._fsp--;

             current =iv_ruleWhileExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleWhileExpression"


    // $ANTLR start "ruleWhileExpression"
    // InternalMyDsl.g:846:1: ruleWhileExpression returns [EObject current=null] : ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_test_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_body_6_0= ruleStatementBody ) ) otherlv_7= '}' ) ;
    public final EObject ruleWhileExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        EObject lv_test_3_0 = null;

        EObject lv_body_6_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:852:2: ( ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_test_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_body_6_0= ruleStatementBody ) ) otherlv_7= '}' ) )
            // InternalMyDsl.g:853:2: ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_test_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_body_6_0= ruleStatementBody ) ) otherlv_7= '}' )
            {
            // InternalMyDsl.g:853:2: ( () otherlv_1= 'while' otherlv_2= '(' ( (lv_test_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_body_6_0= ruleStatementBody ) ) otherlv_7= '}' )
            // InternalMyDsl.g:854:3: () otherlv_1= 'while' otherlv_2= '(' ( (lv_test_3_0= ruleOperationExpression ) ) otherlv_4= ')' otherlv_5= '{' ( (lv_body_6_0= ruleStatementBody ) ) otherlv_7= '}'
            {
            // InternalMyDsl.g:854:3: ()
            // InternalMyDsl.g:855:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getWhileExpressionAccess().getWhileExpressionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,42,FOLLOW_11); 

            			newLeafNode(otherlv_1, grammarAccess.getWhileExpressionAccess().getWhileKeyword_1());
            		
            otherlv_2=(Token)match(input,37,FOLLOW_12); 

            			newLeafNode(otherlv_2, grammarAccess.getWhileExpressionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:869:3: ( (lv_test_3_0= ruleOperationExpression ) )
            // InternalMyDsl.g:870:4: (lv_test_3_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:870:4: (lv_test_3_0= ruleOperationExpression )
            // InternalMyDsl.g:871:5: lv_test_3_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getWhileExpressionAccess().getTestOperationExpressionParserRuleCall_3_0());
            				
            pushFollow(FOLLOW_13);
            lv_test_3_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
            					}
            					set(
            						current,
            						"test",
            						lv_test_3_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_4=(Token)match(input,38,FOLLOW_8); 

            			newLeafNode(otherlv_4, grammarAccess.getWhileExpressionAccess().getRightParenthesisKeyword_4());
            		
            otherlv_5=(Token)match(input,34,FOLLOW_14); 

            			newLeafNode(otherlv_5, grammarAccess.getWhileExpressionAccess().getLeftCurlyBracketKeyword_5());
            		
            // InternalMyDsl.g:896:3: ( (lv_body_6_0= ruleStatementBody ) )
            // InternalMyDsl.g:897:4: (lv_body_6_0= ruleStatementBody )
            {
            // InternalMyDsl.g:897:4: (lv_body_6_0= ruleStatementBody )
            // InternalMyDsl.g:898:5: lv_body_6_0= ruleStatementBody
            {

            					newCompositeNode(grammarAccess.getWhileExpressionAccess().getBodyStatementBodyParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_10);
            lv_body_6_0=ruleStatementBody();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
            					}
            					set(
            						current,
            						"body",
            						lv_body_6_0,
            						"org.xtext.example.mydsl.MyDsl.StatementBody");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,35,FOLLOW_2); 

            			newLeafNode(otherlv_7, grammarAccess.getWhileExpressionAccess().getRightCurlyBracketKeyword_7());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleWhileExpression"


    // $ANTLR start "entryRuleBuiltinFunctionCallExpression"
    // InternalMyDsl.g:923:1: entryRuleBuiltinFunctionCallExpression returns [EObject current=null] : iv_ruleBuiltinFunctionCallExpression= ruleBuiltinFunctionCallExpression EOF ;
    public final EObject entryRuleBuiltinFunctionCallExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuiltinFunctionCallExpression = null;


        try {
            // InternalMyDsl.g:923:70: (iv_ruleBuiltinFunctionCallExpression= ruleBuiltinFunctionCallExpression EOF )
            // InternalMyDsl.g:924:2: iv_ruleBuiltinFunctionCallExpression= ruleBuiltinFunctionCallExpression EOF
            {
             newCompositeNode(grammarAccess.getBuiltinFunctionCallExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBuiltinFunctionCallExpression=ruleBuiltinFunctionCallExpression();

            state._fsp--;

             current =iv_ruleBuiltinFunctionCallExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBuiltinFunctionCallExpression"


    // $ANTLR start "ruleBuiltinFunctionCallExpression"
    // InternalMyDsl.g:930:1: ruleBuiltinFunctionCallExpression returns [EObject current=null] : ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' ) ;
    public final EObject ruleBuiltinFunctionCallExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_function_1_0 = null;

        EObject lv_parameters_3_0 = null;

        EObject lv_parameters_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:936:2: ( ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' ) )
            // InternalMyDsl.g:937:2: ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' )
            {
            // InternalMyDsl.g:937:2: ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' )
            // InternalMyDsl.g:938:3: () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')'
            {
            // InternalMyDsl.g:938:3: ()
            // InternalMyDsl.g:939:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBuiltinFunctionCallExpressionAccess().getBuiltinFunctionCallExpressionAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:945:3: ( (lv_function_1_0= ruleBuiltinFunctions ) )
            // InternalMyDsl.g:946:4: (lv_function_1_0= ruleBuiltinFunctions )
            {
            // InternalMyDsl.g:946:4: (lv_function_1_0= ruleBuiltinFunctions )
            // InternalMyDsl.g:947:5: lv_function_1_0= ruleBuiltinFunctions
            {

            					newCompositeNode(grammarAccess.getBuiltinFunctionCallExpressionAccess().getFunctionBuiltinFunctionsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_11);
            lv_function_1_0=ruleBuiltinFunctions();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBuiltinFunctionCallExpressionRule());
            					}
            					set(
            						current,
            						"function",
            						lv_function_1_0,
            						"org.xtext.example.mydsl.MyDsl.BuiltinFunctions");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,37,FOLLOW_18); 

            			newLeafNode(otherlv_2, grammarAccess.getBuiltinFunctionCallExpressionAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:968:3: ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0>=RULE_ID && LA8_0<=RULE_BOOLEAN)) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // InternalMyDsl.g:969:4: ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )*
                    {
                    // InternalMyDsl.g:969:4: ( (lv_parameters_3_0= ruleAtomic ) )
                    // InternalMyDsl.g:970:5: (lv_parameters_3_0= ruleAtomic )
                    {
                    // InternalMyDsl.g:970:5: (lv_parameters_3_0= ruleAtomic )
                    // InternalMyDsl.g:971:6: lv_parameters_3_0= ruleAtomic
                    {

                    						newCompositeNode(grammarAccess.getBuiltinFunctionCallExpressionAccess().getParametersAtomicParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_19);
                    lv_parameters_3_0=ruleAtomic();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getBuiltinFunctionCallExpressionRule());
                    						}
                    						add(
                    							current,
                    							"parameters",
                    							lv_parameters_3_0,
                    							"org.xtext.example.mydsl.MyDsl.Atomic");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMyDsl.g:988:4: (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==43) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalMyDsl.g:989:5: otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) )
                    	    {
                    	    otherlv_4=(Token)match(input,43,FOLLOW_12); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getBuiltinFunctionCallExpressionAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalMyDsl.g:993:5: ( (lv_parameters_5_0= ruleAtomic ) )
                    	    // InternalMyDsl.g:994:6: (lv_parameters_5_0= ruleAtomic )
                    	    {
                    	    // InternalMyDsl.g:994:6: (lv_parameters_5_0= ruleAtomic )
                    	    // InternalMyDsl.g:995:7: lv_parameters_5_0= ruleAtomic
                    	    {

                    	    							newCompositeNode(grammarAccess.getBuiltinFunctionCallExpressionAccess().getParametersAtomicParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_19);
                    	    lv_parameters_5_0=ruleAtomic();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getBuiltinFunctionCallExpressionRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"parameters",
                    	    								lv_parameters_5_0,
                    	    								"org.xtext.example.mydsl.MyDsl.Atomic");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,38,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getBuiltinFunctionCallExpressionAccess().getRightParenthesisKeyword_4());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBuiltinFunctionCallExpression"


    // $ANTLR start "entryRuleTaskBody"
    // InternalMyDsl.g:1022:1: entryRuleTaskBody returns [EObject current=null] : iv_ruleTaskBody= ruleTaskBody EOF ;
    public final EObject entryRuleTaskBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTaskBody = null;


        try {
            // InternalMyDsl.g:1022:49: (iv_ruleTaskBody= ruleTaskBody EOF )
            // InternalMyDsl.g:1023:2: iv_ruleTaskBody= ruleTaskBody EOF
            {
             newCompositeNode(grammarAccess.getTaskBodyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleTaskBody=ruleTaskBody();

            state._fsp--;

             current =iv_ruleTaskBody; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTaskBody"


    // $ANTLR start "ruleTaskBody"
    // InternalMyDsl.g:1029:1: ruleTaskBody returns [EObject current=null] : ( () ( (lv_body_1_0= ruleVariableDeclerationExpression ) )* ( (lv_body_2_0= ruleBlockExpressions ) )* ( (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) ) | ( (lv_endtask_5_0= 'end' ) ) ) ) ;
    public final EObject ruleTaskBody() throws RecognitionException {
        EObject current = null;

        Token otherlv_3=null;
        Token otherlv_4=null;
        Token lv_endtask_5_0=null;
        EObject lv_body_1_0 = null;

        EObject lv_body_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1035:2: ( ( () ( (lv_body_1_0= ruleVariableDeclerationExpression ) )* ( (lv_body_2_0= ruleBlockExpressions ) )* ( (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) ) | ( (lv_endtask_5_0= 'end' ) ) ) ) )
            // InternalMyDsl.g:1036:2: ( () ( (lv_body_1_0= ruleVariableDeclerationExpression ) )* ( (lv_body_2_0= ruleBlockExpressions ) )* ( (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) ) | ( (lv_endtask_5_0= 'end' ) ) ) )
            {
            // InternalMyDsl.g:1036:2: ( () ( (lv_body_1_0= ruleVariableDeclerationExpression ) )* ( (lv_body_2_0= ruleBlockExpressions ) )* ( (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) ) | ( (lv_endtask_5_0= 'end' ) ) ) )
            // InternalMyDsl.g:1037:3: () ( (lv_body_1_0= ruleVariableDeclerationExpression ) )* ( (lv_body_2_0= ruleBlockExpressions ) )* ( (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) ) | ( (lv_endtask_5_0= 'end' ) ) )
            {
            // InternalMyDsl.g:1037:3: ()
            // InternalMyDsl.g:1038:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getTaskBodyAccess().getTaskBodyAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:1044:3: ( (lv_body_1_0= ruleVariableDeclerationExpression ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>=28 && LA9_0<=30)) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalMyDsl.g:1045:4: (lv_body_1_0= ruleVariableDeclerationExpression )
            	    {
            	    // InternalMyDsl.g:1045:4: (lv_body_1_0= ruleVariableDeclerationExpression )
            	    // InternalMyDsl.g:1046:5: lv_body_1_0= ruleVariableDeclerationExpression
            	    {

            	    					newCompositeNode(grammarAccess.getTaskBodyAccess().getBodyVariableDeclerationExpressionParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_body_1_0=ruleVariableDeclerationExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTaskBodyRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_1_0,
            	    						"org.xtext.example.mydsl.MyDsl.VariableDeclerationExpression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // InternalMyDsl.g:1063:3: ( (lv_body_2_0= ruleBlockExpressions ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_BOOLEAN)||LA10_0==26||LA10_0==36||LA10_0==40||LA10_0==42) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalMyDsl.g:1064:4: (lv_body_2_0= ruleBlockExpressions )
            	    {
            	    // InternalMyDsl.g:1064:4: (lv_body_2_0= ruleBlockExpressions )
            	    // InternalMyDsl.g:1065:5: lv_body_2_0= ruleBlockExpressions
            	    {

            	    					newCompositeNode(grammarAccess.getTaskBodyAccess().getBodyBlockExpressionsParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_20);
            	    lv_body_2_0=ruleBlockExpressions();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTaskBodyRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_2_0,
            	    						"org.xtext.example.mydsl.MyDsl.BlockExpressions");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            // InternalMyDsl.g:1082:3: ( (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) ) | ( (lv_endtask_5_0= 'end' ) ) )
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==44) ) {
                alt11=1;
            }
            else if ( (LA11_0==45) ) {
                alt11=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 11, 0, input);

                throw nvae;
            }
            switch (alt11) {
                case 1 :
                    // InternalMyDsl.g:1083:4: (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) )
                    {
                    // InternalMyDsl.g:1083:4: (otherlv_3= 'next' ( (otherlv_4= RULE_ID ) ) )
                    // InternalMyDsl.g:1084:5: otherlv_3= 'next' ( (otherlv_4= RULE_ID ) )
                    {
                    otherlv_3=(Token)match(input,44,FOLLOW_7); 

                    					newLeafNode(otherlv_3, grammarAccess.getTaskBodyAccess().getNextKeyword_3_0_0());
                    				
                    // InternalMyDsl.g:1088:5: ( (otherlv_4= RULE_ID ) )
                    // InternalMyDsl.g:1089:6: (otherlv_4= RULE_ID )
                    {
                    // InternalMyDsl.g:1089:6: (otherlv_4= RULE_ID )
                    // InternalMyDsl.g:1090:7: otherlv_4= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getTaskBodyRule());
                    							}
                    						
                    otherlv_4=(Token)match(input,RULE_ID,FOLLOW_2); 

                    							newLeafNode(otherlv_4, grammarAccess.getTaskBodyAccess().getNexttaskTaskCrossReference_3_0_1_0());
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1103:4: ( (lv_endtask_5_0= 'end' ) )
                    {
                    // InternalMyDsl.g:1103:4: ( (lv_endtask_5_0= 'end' ) )
                    // InternalMyDsl.g:1104:5: (lv_endtask_5_0= 'end' )
                    {
                    // InternalMyDsl.g:1104:5: (lv_endtask_5_0= 'end' )
                    // InternalMyDsl.g:1105:6: lv_endtask_5_0= 'end'
                    {
                    lv_endtask_5_0=(Token)match(input,45,FOLLOW_2); 

                    						newLeafNode(lv_endtask_5_0, grammarAccess.getTaskBodyAccess().getEndtaskEndKeyword_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getTaskBodyRule());
                    						}
                    						setWithLastConsumed(current, "endtask", lv_endtask_5_0, "end");
                    					

                    }


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTaskBody"


    // $ANTLR start "entryRuleStatementBody"
    // InternalMyDsl.g:1122:1: entryRuleStatementBody returns [EObject current=null] : iv_ruleStatementBody= ruleStatementBody EOF ;
    public final EObject entryRuleStatementBody() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStatementBody = null;


        try {
            // InternalMyDsl.g:1122:54: (iv_ruleStatementBody= ruleStatementBody EOF )
            // InternalMyDsl.g:1123:2: iv_ruleStatementBody= ruleStatementBody EOF
            {
             newCompositeNode(grammarAccess.getStatementBodyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStatementBody=ruleStatementBody();

            state._fsp--;

             current =iv_ruleStatementBody; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleStatementBody"


    // $ANTLR start "ruleStatementBody"
    // InternalMyDsl.g:1129:1: ruleStatementBody returns [EObject current=null] : ( () ( (lv_body_1_0= ruleBlockExpressions ) )* ( (lv_body_2_0= ruleBreakExpression ) )? ) ;
    public final EObject ruleStatementBody() throws RecognitionException {
        EObject current = null;

        EObject lv_body_1_0 = null;

        EObject lv_body_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1135:2: ( ( () ( (lv_body_1_0= ruleBlockExpressions ) )* ( (lv_body_2_0= ruleBreakExpression ) )? ) )
            // InternalMyDsl.g:1136:2: ( () ( (lv_body_1_0= ruleBlockExpressions ) )* ( (lv_body_2_0= ruleBreakExpression ) )? )
            {
            // InternalMyDsl.g:1136:2: ( () ( (lv_body_1_0= ruleBlockExpressions ) )* ( (lv_body_2_0= ruleBreakExpression ) )? )
            // InternalMyDsl.g:1137:3: () ( (lv_body_1_0= ruleBlockExpressions ) )* ( (lv_body_2_0= ruleBreakExpression ) )?
            {
            // InternalMyDsl.g:1137:3: ()
            // InternalMyDsl.g:1138:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getStatementBodyAccess().getStatementBodyAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:1144:3: ( (lv_body_1_0= ruleBlockExpressions ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>=RULE_ID && LA12_0<=RULE_BOOLEAN)||LA12_0==26||LA12_0==36||LA12_0==40||LA12_0==42) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalMyDsl.g:1145:4: (lv_body_1_0= ruleBlockExpressions )
            	    {
            	    // InternalMyDsl.g:1145:4: (lv_body_1_0= ruleBlockExpressions )
            	    // InternalMyDsl.g:1146:5: lv_body_1_0= ruleBlockExpressions
            	    {

            	    					newCompositeNode(grammarAccess.getStatementBodyAccess().getBodyBlockExpressionsParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_21);
            	    lv_body_1_0=ruleBlockExpressions();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getStatementBodyRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_1_0,
            	    						"org.xtext.example.mydsl.MyDsl.BlockExpressions");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // InternalMyDsl.g:1163:3: ( (lv_body_2_0= ruleBreakExpression ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==27) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalMyDsl.g:1164:4: (lv_body_2_0= ruleBreakExpression )
                    {
                    // InternalMyDsl.g:1164:4: (lv_body_2_0= ruleBreakExpression )
                    // InternalMyDsl.g:1165:5: lv_body_2_0= ruleBreakExpression
                    {

                    					newCompositeNode(grammarAccess.getStatementBodyAccess().getBodyBreakExpressionParserRuleCall_2_0());
                    				
                    pushFollow(FOLLOW_2);
                    lv_body_2_0=ruleBreakExpression();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getStatementBodyRule());
                    					}
                    					add(
                    						current,
                    						"body",
                    						lv_body_2_0,
                    						"org.xtext.example.mydsl.MyDsl.BreakExpression");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleStatementBody"


    // $ANTLR start "entryRuleBlockExpressions"
    // InternalMyDsl.g:1186:1: entryRuleBlockExpressions returns [EObject current=null] : iv_ruleBlockExpressions= ruleBlockExpressions EOF ;
    public final EObject entryRuleBlockExpressions() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBlockExpressions = null;


        try {
            // InternalMyDsl.g:1186:57: (iv_ruleBlockExpressions= ruleBlockExpressions EOF )
            // InternalMyDsl.g:1187:2: iv_ruleBlockExpressions= ruleBlockExpressions EOF
            {
             newCompositeNode(grammarAccess.getBlockExpressionsRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBlockExpressions=ruleBlockExpressions();

            state._fsp--;

             current =iv_ruleBlockExpressions; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleBlockExpressions"


    // $ANTLR start "ruleBlockExpressions"
    // InternalMyDsl.g:1193:1: ruleBlockExpressions returns [EObject current=null] : (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCallExpression_4= ruleBuiltinFunctionCallExpression ) ;
    public final EObject ruleBlockExpressions() throws RecognitionException {
        EObject current = null;

        EObject this_OperationExpression_0 = null;

        EObject this_IfExpression_1 = null;

        EObject this_ForExpression_2 = null;

        EObject this_WhileExpression_3 = null;

        EObject this_BuiltinFunctionCallExpression_4 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1199:2: ( (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCallExpression_4= ruleBuiltinFunctionCallExpression ) )
            // InternalMyDsl.g:1200:2: (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCallExpression_4= ruleBuiltinFunctionCallExpression )
            {
            // InternalMyDsl.g:1200:2: (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCallExpression_4= ruleBuiltinFunctionCallExpression )
            int alt14=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case RULE_BOOLEAN:
                {
                alt14=1;
                }
                break;
            case 36:
                {
                alt14=2;
                }
                break;
            case 40:
                {
                alt14=3;
                }
                break;
            case 42:
                {
                alt14=4;
                }
                break;
            case 26:
                {
                alt14=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;
            }

            switch (alt14) {
                case 1 :
                    // InternalMyDsl.g:1201:3: this_OperationExpression_0= ruleOperationExpression
                    {

                    			newCompositeNode(grammarAccess.getBlockExpressionsAccess().getOperationExpressionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_OperationExpression_0=ruleOperationExpression();

                    state._fsp--;


                    			current = this_OperationExpression_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1210:3: this_IfExpression_1= ruleIfExpression
                    {

                    			newCompositeNode(grammarAccess.getBlockExpressionsAccess().getIfExpressionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_IfExpression_1=ruleIfExpression();

                    state._fsp--;


                    			current = this_IfExpression_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:1219:3: this_ForExpression_2= ruleForExpression
                    {

                    			newCompositeNode(grammarAccess.getBlockExpressionsAccess().getForExpressionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ForExpression_2=ruleForExpression();

                    state._fsp--;


                    			current = this_ForExpression_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:1228:3: this_WhileExpression_3= ruleWhileExpression
                    {

                    			newCompositeNode(grammarAccess.getBlockExpressionsAccess().getWhileExpressionParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_WhileExpression_3=ruleWhileExpression();

                    state._fsp--;


                    			current = this_WhileExpression_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:1237:3: this_BuiltinFunctionCallExpression_4= ruleBuiltinFunctionCallExpression
                    {

                    			newCompositeNode(grammarAccess.getBlockExpressionsAccess().getBuiltinFunctionCallExpressionParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_BuiltinFunctionCallExpression_4=ruleBuiltinFunctionCallExpression();

                    state._fsp--;


                    			current = this_BuiltinFunctionCallExpression_4;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleBlockExpressions"


    // $ANTLR start "entryRuleOperationExpression"
    // InternalMyDsl.g:1249:1: entryRuleOperationExpression returns [EObject current=null] : iv_ruleOperationExpression= ruleOperationExpression EOF ;
    public final EObject entryRuleOperationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationExpression = null;


        try {
            // InternalMyDsl.g:1249:60: (iv_ruleOperationExpression= ruleOperationExpression EOF )
            // InternalMyDsl.g:1250:2: iv_ruleOperationExpression= ruleOperationExpression EOF
            {
             newCompositeNode(grammarAccess.getOperationExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperationExpression=ruleOperationExpression();

            state._fsp--;

             current =iv_ruleOperationExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperationExpression"


    // $ANTLR start "ruleOperationExpression"
    // InternalMyDsl.g:1256:1: ruleOperationExpression returns [EObject current=null] : this_Operation_0= ruleOperation ;
    public final EObject ruleOperationExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Operation_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1262:2: (this_Operation_0= ruleOperation )
            // InternalMyDsl.g:1263:2: this_Operation_0= ruleOperation
            {

            		newCompositeNode(grammarAccess.getOperationExpressionAccess().getOperationParserRuleCall());
            	
            pushFollow(FOLLOW_2);
            this_Operation_0=ruleOperation();

            state._fsp--;


            		current = this_Operation_0;
            		afterParserOrEnumRuleCall();
            	

            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperationExpression"


    // $ANTLR start "entryRuleOperation"
    // InternalMyDsl.g:1274:1: entryRuleOperation returns [EObject current=null] : iv_ruleOperation= ruleOperation EOF ;
    public final EObject entryRuleOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation = null;


        try {
            // InternalMyDsl.g:1274:50: (iv_ruleOperation= ruleOperation EOF )
            // InternalMyDsl.g:1275:2: iv_ruleOperation= ruleOperation EOF
            {
             newCompositeNode(grammarAccess.getOperationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleOperation=ruleOperation();

            state._fsp--;

             current =iv_ruleOperation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleOperation"


    // $ANTLR start "ruleOperation"
    // InternalMyDsl.g:1281:1: ruleOperation returns [EObject current=null] : (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )* ) ) ;
    public final EObject ruleOperation() throws RecognitionException {
        EObject current = null;

        EObject this_Atomic_0 = null;

        AntlrDatatypeRuleToken lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1287:2: ( (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )* ) ) )
            // InternalMyDsl.g:1288:2: (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )* ) )
            {
            // InternalMyDsl.g:1288:2: (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )* ) )
            // InternalMyDsl.g:1289:3: this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )* )
            {

            			newCompositeNode(grammarAccess.getOperationAccess().getAtomicParserRuleCall_0());
            		
            pushFollow(FOLLOW_22);
            this_Atomic_0=ruleAtomic();

            state._fsp--;


            			current = this_Atomic_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMyDsl.g:1297:3: ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )* )
            // InternalMyDsl.g:1298:4: () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )*
            {
            // InternalMyDsl.g:1298:4: ()
            // InternalMyDsl.g:1299:5: 
            {

            					current = forceCreateModelElementAndSet(
            						grammarAccess.getOperationAccess().getOperationLeftAction_1_0(),
            						current);
            				

            }

            // InternalMyDsl.g:1305:4: ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) ) )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>=13 && LA15_0<=25)) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // InternalMyDsl.g:1306:5: ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomic ) )
            	    {
            	    // InternalMyDsl.g:1306:5: ( (lv_operator_2_0= ruleOperator ) )
            	    // InternalMyDsl.g:1307:6: (lv_operator_2_0= ruleOperator )
            	    {
            	    // InternalMyDsl.g:1307:6: (lv_operator_2_0= ruleOperator )
            	    // InternalMyDsl.g:1308:7: lv_operator_2_0= ruleOperator
            	    {

            	    							newCompositeNode(grammarAccess.getOperationAccess().getOperatorOperatorParserRuleCall_1_1_0_0());
            	    						
            	    pushFollow(FOLLOW_12);
            	    lv_operator_2_0=ruleOperator();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getOperationRule());
            	    							}
            	    							add(
            	    								current,
            	    								"operator",
            	    								lv_operator_2_0,
            	    								"org.xtext.example.mydsl.MyDsl.Operator");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }

            	    // InternalMyDsl.g:1325:5: ( (lv_right_3_0= ruleAtomic ) )
            	    // InternalMyDsl.g:1326:6: (lv_right_3_0= ruleAtomic )
            	    {
            	    // InternalMyDsl.g:1326:6: (lv_right_3_0= ruleAtomic )
            	    // InternalMyDsl.g:1327:7: lv_right_3_0= ruleAtomic
            	    {

            	    							newCompositeNode(grammarAccess.getOperationAccess().getRightAtomicParserRuleCall_1_1_1_0());
            	    						
            	    pushFollow(FOLLOW_22);
            	    lv_right_3_0=ruleAtomic();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getOperationRule());
            	    							}
            	    							add(
            	    								current,
            	    								"right",
            	    								lv_right_3_0,
            	    								"org.xtext.example.mydsl.MyDsl.Atomic");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleOperation"


    // $ANTLR start "entryRuleArrayDimension"
    // InternalMyDsl.g:1350:1: entryRuleArrayDimension returns [EObject current=null] : iv_ruleArrayDimension= ruleArrayDimension EOF ;
    public final EObject entryRuleArrayDimension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayDimension = null;


        try {
            // InternalMyDsl.g:1350:55: (iv_ruleArrayDimension= ruleArrayDimension EOF )
            // InternalMyDsl.g:1351:2: iv_ruleArrayDimension= ruleArrayDimension EOF
            {
             newCompositeNode(grammarAccess.getArrayDimensionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleArrayDimension=ruleArrayDimension();

            state._fsp--;

             current =iv_ruleArrayDimension; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleArrayDimension"


    // $ANTLR start "ruleArrayDimension"
    // InternalMyDsl.g:1357:1: ruleArrayDimension returns [EObject current=null] : ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' ) ;
    public final EObject ruleArrayDimension() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_size_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalMyDsl.g:1363:2: ( ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' ) )
            // InternalMyDsl.g:1364:2: ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' )
            {
            // InternalMyDsl.g:1364:2: ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' )
            // InternalMyDsl.g:1365:3: () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']'
            {
            // InternalMyDsl.g:1365:3: ()
            // InternalMyDsl.g:1366:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getArrayDimensionAccess().getArrayDimensionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,46,FOLLOW_23); 

            			newLeafNode(otherlv_1, grammarAccess.getArrayDimensionAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalMyDsl.g:1376:3: ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_ID) ) {
                alt16=1;
            }
            else if ( (LA16_0==RULE_INT) ) {
                alt16=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // InternalMyDsl.g:1377:4: ( (otherlv_2= RULE_ID ) )
                    {
                    // InternalMyDsl.g:1377:4: ( (otherlv_2= RULE_ID ) )
                    // InternalMyDsl.g:1378:5: (otherlv_2= RULE_ID )
                    {
                    // InternalMyDsl.g:1378:5: (otherlv_2= RULE_ID )
                    // InternalMyDsl.g:1379:6: otherlv_2= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getArrayDimensionRule());
                    						}
                    					
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_24); 

                    						newLeafNode(otherlv_2, grammarAccess.getArrayDimensionAccess().getIndexVariableSymbolCrossReference_2_0_0());
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1391:4: ( (lv_size_3_0= RULE_INT ) )
                    {
                    // InternalMyDsl.g:1391:4: ( (lv_size_3_0= RULE_INT ) )
                    // InternalMyDsl.g:1392:5: (lv_size_3_0= RULE_INT )
                    {
                    // InternalMyDsl.g:1392:5: (lv_size_3_0= RULE_INT )
                    // InternalMyDsl.g:1393:6: lv_size_3_0= RULE_INT
                    {
                    lv_size_3_0=(Token)match(input,RULE_INT,FOLLOW_24); 

                    						newLeafNode(lv_size_3_0, grammarAccess.getArrayDimensionAccess().getSizeINTTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getArrayDimensionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"size",
                    							lv_size_3_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }
                    break;

            }

            otherlv_4=(Token)match(input,47,FOLLOW_2); 

            			newLeafNode(otherlv_4, grammarAccess.getArrayDimensionAccess().getRightSquareBracketKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleArrayDimension"


    // $ANTLR start "entryRuleVariableDeclerationExpression"
    // InternalMyDsl.g:1418:1: entryRuleVariableDeclerationExpression returns [EObject current=null] : iv_ruleVariableDeclerationExpression= ruleVariableDeclerationExpression EOF ;
    public final EObject entryRuleVariableDeclerationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableDeclerationExpression = null;


        try {
            // InternalMyDsl.g:1418:70: (iv_ruleVariableDeclerationExpression= ruleVariableDeclerationExpression EOF )
            // InternalMyDsl.g:1419:2: iv_ruleVariableDeclerationExpression= ruleVariableDeclerationExpression EOF
            {
             newCompositeNode(grammarAccess.getVariableDeclerationExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableDeclerationExpression=ruleVariableDeclerationExpression();

            state._fsp--;

             current =iv_ruleVariableDeclerationExpression; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleVariableDeclerationExpression"


    // $ANTLR start "ruleVariableDeclerationExpression"
    // InternalMyDsl.g:1425:1: ruleVariableDeclerationExpression returns [EObject current=null] : ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) ) ;
    public final EObject ruleVariableDeclerationExpression() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token lv_name_2_0=null;
        EObject lv_type_0_0 = null;

        EObject lv_dimension_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1431:2: ( ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) ) )
            // InternalMyDsl.g:1432:2: ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) )
            {
            // InternalMyDsl.g:1432:2: ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) )
            // InternalMyDsl.g:1433:3: ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) )
            {
            // InternalMyDsl.g:1433:3: ( (lv_type_0_0= ruleVariableType ) )
            // InternalMyDsl.g:1434:4: (lv_type_0_0= ruleVariableType )
            {
            // InternalMyDsl.g:1434:4: (lv_type_0_0= ruleVariableType )
            // InternalMyDsl.g:1435:5: lv_type_0_0= ruleVariableType
            {

            					newCompositeNode(grammarAccess.getVariableDeclerationExpressionAccess().getTypeVariableTypeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_7);
            lv_type_0_0=ruleVariableType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariableDeclerationExpressionRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"org.xtext.example.mydsl.MyDsl.VariableType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:1452:3: ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==RULE_ID) ) {
                int LA18_1 = input.LA(2);

                if ( (LA18_1==46) ) {
                    alt18=2;
                }
                else if ( (LA18_1==EOF||(LA18_1>=RULE_ID && LA18_1<=RULE_BOOLEAN)||LA18_1==26||(LA18_1>=28 && LA18_1<=33)||LA18_1==36||LA18_1==40||LA18_1==42||(LA18_1>=44 && LA18_1<=45)) ) {
                    alt18=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 18, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // InternalMyDsl.g:1453:4: ( (lv_name_1_0= RULE_ID ) )
                    {
                    // InternalMyDsl.g:1453:4: ( (lv_name_1_0= RULE_ID ) )
                    // InternalMyDsl.g:1454:5: (lv_name_1_0= RULE_ID )
                    {
                    // InternalMyDsl.g:1454:5: (lv_name_1_0= RULE_ID )
                    // InternalMyDsl.g:1455:6: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(lv_name_1_0, grammarAccess.getVariableDeclerationExpressionAccess().getNameIDTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableDeclerationExpressionRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"name",
                    							lv_name_1_0,
                    							"org.eclipse.xtext.common.Terminals.ID");
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1472:4: ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ )
                    {
                    // InternalMyDsl.g:1472:4: ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ )
                    // InternalMyDsl.g:1473:5: ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+
                    {
                    // InternalMyDsl.g:1473:5: ( (lv_name_2_0= RULE_ID ) )
                    // InternalMyDsl.g:1474:6: (lv_name_2_0= RULE_ID )
                    {
                    // InternalMyDsl.g:1474:6: (lv_name_2_0= RULE_ID )
                    // InternalMyDsl.g:1475:7: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_25); 

                    							newLeafNode(lv_name_2_0, grammarAccess.getVariableDeclerationExpressionAccess().getNameIDTerminalRuleCall_1_1_0_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getVariableDeclerationExpressionRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"name",
                    								lv_name_2_0,
                    								"org.eclipse.xtext.common.Terminals.ID");
                    						

                    }


                    }

                    // InternalMyDsl.g:1491:5: ( (lv_dimension_3_0= ruleArrayDimension ) )+
                    int cnt17=0;
                    loop17:
                    do {
                        int alt17=2;
                        int LA17_0 = input.LA(1);

                        if ( (LA17_0==46) ) {
                            alt17=1;
                        }


                        switch (alt17) {
                    	case 1 :
                    	    // InternalMyDsl.g:1492:6: (lv_dimension_3_0= ruleArrayDimension )
                    	    {
                    	    // InternalMyDsl.g:1492:6: (lv_dimension_3_0= ruleArrayDimension )
                    	    // InternalMyDsl.g:1493:7: lv_dimension_3_0= ruleArrayDimension
                    	    {

                    	    							newCompositeNode(grammarAccess.getVariableDeclerationExpressionAccess().getDimensionArrayDimensionParserRuleCall_1_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_26);
                    	    lv_dimension_3_0=ruleArrayDimension();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getVariableDeclerationExpressionRule());
                    	    							}
                    	    							add(
                    	    								current,
                    	    								"dimension",
                    	    								lv_dimension_3_0,
                    	    								"org.xtext.example.mydsl.MyDsl.ArrayDimension");
                    	    							afterParserOrEnumRuleCall();
                    	    						

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt17 >= 1 ) break loop17;
                                EarlyExitException eee =
                                    new EarlyExitException(17, input);
                                throw eee;
                        }
                        cnt17++;
                    } while (true);


                    }


                    }
                    break;

            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariableDeclerationExpression"


    // $ANTLR start "entryRuleAtomic"
    // InternalMyDsl.g:1516:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // InternalMyDsl.g:1516:47: (iv_ruleAtomic= ruleAtomic EOF )
            // InternalMyDsl.g:1517:2: iv_ruleAtomic= ruleAtomic EOF
            {
             newCompositeNode(grammarAccess.getAtomicRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomic=ruleAtomic();

            state._fsp--;

             current =iv_ruleAtomic; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleAtomic"


    // $ANTLR start "ruleAtomic"
    // InternalMyDsl.g:1523:1: ruleAtomic returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) ) ;
    public final EObject ruleAtomic() throws RecognitionException {
        EObject current = null;

        Token lv_value_1_0=null;
        Token lv_value_3_0=null;
        Token lv_value_5_0=null;
        Token lv_value_7_0=null;
        Token otherlv_9=null;
        Token otherlv_12=null;
        EObject lv_dimension_10_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1529:2: ( ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) ) )
            // InternalMyDsl.g:1530:2: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) )
            {
            // InternalMyDsl.g:1530:2: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) )
            int alt20=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt20=1;
                }
                break;
            case RULE_DOUBLE:
                {
                alt20=2;
                }
                break;
            case RULE_STRING:
                {
                alt20=3;
                }
                break;
            case RULE_BOOLEAN:
                {
                alt20=4;
                }
                break;
            case RULE_ID:
                {
                int LA20_5 = input.LA(2);

                if ( (LA20_5==EOF||(LA20_5>=RULE_ID && LA20_5<=RULE_BOOLEAN)||(LA20_5>=13 && LA20_5<=27)||(LA20_5>=35 && LA20_5<=36)||LA20_5==38||(LA20_5>=40 && LA20_5<=45)) ) {
                    alt20=6;
                }
                else if ( (LA20_5==46) ) {
                    alt20=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 20, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // InternalMyDsl.g:1531:3: ( () ( (lv_value_1_0= RULE_INT ) ) )
                    {
                    // InternalMyDsl.g:1531:3: ( () ( (lv_value_1_0= RULE_INT ) ) )
                    // InternalMyDsl.g:1532:4: () ( (lv_value_1_0= RULE_INT ) )
                    {
                    // InternalMyDsl.g:1532:4: ()
                    // InternalMyDsl.g:1533:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getIntegerReferenceAction_0_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1539:4: ( (lv_value_1_0= RULE_INT ) )
                    // InternalMyDsl.g:1540:5: (lv_value_1_0= RULE_INT )
                    {
                    // InternalMyDsl.g:1540:5: (lv_value_1_0= RULE_INT )
                    // InternalMyDsl.g:1541:6: lv_value_1_0= RULE_INT
                    {
                    lv_value_1_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_value_1_0, grammarAccess.getAtomicAccess().getValueINTTerminalRuleCall_0_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_1_0,
                    							"org.eclipse.xtext.common.Terminals.INT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1559:3: ( () ( (lv_value_3_0= RULE_DOUBLE ) ) )
                    {
                    // InternalMyDsl.g:1559:3: ( () ( (lv_value_3_0= RULE_DOUBLE ) ) )
                    // InternalMyDsl.g:1560:4: () ( (lv_value_3_0= RULE_DOUBLE ) )
                    {
                    // InternalMyDsl.g:1560:4: ()
                    // InternalMyDsl.g:1561:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getDoubleReferenceAction_1_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1567:4: ( (lv_value_3_0= RULE_DOUBLE ) )
                    // InternalMyDsl.g:1568:5: (lv_value_3_0= RULE_DOUBLE )
                    {
                    // InternalMyDsl.g:1568:5: (lv_value_3_0= RULE_DOUBLE )
                    // InternalMyDsl.g:1569:6: lv_value_3_0= RULE_DOUBLE
                    {
                    lv_value_3_0=(Token)match(input,RULE_DOUBLE,FOLLOW_2); 

                    						newLeafNode(lv_value_3_0, grammarAccess.getAtomicAccess().getValueDOUBLETerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_3_0,
                    							"org.xtext.example.mydsl.MyDsl.DOUBLE");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:1587:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalMyDsl.g:1587:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalMyDsl.g:1588:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalMyDsl.g:1588:4: ()
                    // InternalMyDsl.g:1589:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getStringReferenceAction_2_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1595:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalMyDsl.g:1596:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalMyDsl.g:1596:5: (lv_value_5_0= RULE_STRING )
                    // InternalMyDsl.g:1597:6: lv_value_5_0= RULE_STRING
                    {
                    lv_value_5_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_value_5_0, grammarAccess.getAtomicAccess().getValueSTRINGTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_5_0,
                    							"org.eclipse.xtext.common.Terminals.STRING");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:1615:3: ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) )
                    {
                    // InternalMyDsl.g:1615:3: ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) )
                    // InternalMyDsl.g:1616:4: () ( (lv_value_7_0= RULE_BOOLEAN ) )
                    {
                    // InternalMyDsl.g:1616:4: ()
                    // InternalMyDsl.g:1617:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getBooleanReferenceAction_3_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1623:4: ( (lv_value_7_0= RULE_BOOLEAN ) )
                    // InternalMyDsl.g:1624:5: (lv_value_7_0= RULE_BOOLEAN )
                    {
                    // InternalMyDsl.g:1624:5: (lv_value_7_0= RULE_BOOLEAN )
                    // InternalMyDsl.g:1625:6: lv_value_7_0= RULE_BOOLEAN
                    {
                    lv_value_7_0=(Token)match(input,RULE_BOOLEAN,FOLLOW_2); 

                    						newLeafNode(lv_value_7_0, grammarAccess.getAtomicAccess().getValueBOOLEANTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"value",
                    							lv_value_7_0,
                    							"org.xtext.example.mydsl.MyDsl.BOOLEAN");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:1643:3: ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ )
                    {
                    // InternalMyDsl.g:1643:3: ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ )
                    // InternalMyDsl.g:1644:4: () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+
                    {
                    // InternalMyDsl.g:1644:4: ()
                    // InternalMyDsl.g:1645:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getArrayReferenceAction_4_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1651:4: ( (otherlv_9= RULE_ID ) )
                    // InternalMyDsl.g:1652:5: (otherlv_9= RULE_ID )
                    {
                    // InternalMyDsl.g:1652:5: (otherlv_9= RULE_ID )
                    // InternalMyDsl.g:1653:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_25); 

                    						newLeafNode(otherlv_9, grammarAccess.getAtomicAccess().getVariableReferenceVariableSymbolCrossReference_4_1_0());
                    					

                    }


                    }

                    // InternalMyDsl.g:1664:4: ( (lv_dimension_10_0= ruleArrayDimension ) )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==46) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // InternalMyDsl.g:1665:5: (lv_dimension_10_0= ruleArrayDimension )
                    	    {
                    	    // InternalMyDsl.g:1665:5: (lv_dimension_10_0= ruleArrayDimension )
                    	    // InternalMyDsl.g:1666:6: lv_dimension_10_0= ruleArrayDimension
                    	    {

                    	    						newCompositeNode(grammarAccess.getAtomicAccess().getDimensionArrayDimensionParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_26);
                    	    lv_dimension_10_0=ruleArrayDimension();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getAtomicRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"dimension",
                    	    							lv_dimension_10_0,
                    	    							"org.xtext.example.mydsl.MyDsl.ArrayDimension");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);


                    }


                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:1685:3: ( () ( (otherlv_12= RULE_ID ) ) )
                    {
                    // InternalMyDsl.g:1685:3: ( () ( (otherlv_12= RULE_ID ) ) )
                    // InternalMyDsl.g:1686:4: () ( (otherlv_12= RULE_ID ) )
                    {
                    // InternalMyDsl.g:1686:4: ()
                    // InternalMyDsl.g:1687:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getVariableReferenceAction_5_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1693:4: ( (otherlv_12= RULE_ID ) )
                    // InternalMyDsl.g:1694:5: (otherlv_12= RULE_ID )
                    {
                    // InternalMyDsl.g:1694:5: (otherlv_12= RULE_ID )
                    // InternalMyDsl.g:1695:6: otherlv_12= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    					
                    otherlv_12=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(otherlv_12, grammarAccess.getAtomicAccess().getVariableReferenceVariableSymbolCrossReference_5_1_0());
                    					

                    }


                    }


                    }


                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtomic"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000380000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000070000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00003510740001F0L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x000005180C0001F0L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x00000040000001F0L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000084000000000L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x00003510040001F0L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x000005100C0001F2L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000000003FFE002L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000400000000002L});

}