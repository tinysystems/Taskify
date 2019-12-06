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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ID", "RULE_INT", "RULE_DOUBLE", "RULE_STRING", "RULE_BOOLEAN", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'-'", "'+'", "'/'", "'*'", "'>='", "'<='", "'=='", "'!='", "'<'", "'>'", "'='", "'AND'", "'OR'", "'print'", "'break'", "'string'", "'integer'", "'boolean'", "'GLOBAL'", "'entry'", "'task'", "'{'", "'next'", "'end'", "'}'", "'if'", "'('", "')'", "'else'", "'for'", "';'", "'while'", "'['", "']'", "','"
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
    // InternalMyDsl.g:71:1: ruleInkApp returns [EObject current=null] : ( ( (lv_globals_0_0= ruleGlobalVariable ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) ) ;
    public final EObject ruleInkApp() throws RecognitionException {
        EObject current = null;

        EObject lv_globals_0_0 = null;

        EObject lv_tasks_1_0 = null;

        EObject lv_entry_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:77:2: ( ( ( (lv_globals_0_0= ruleGlobalVariable ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) ) )
            // InternalMyDsl.g:78:2: ( ( (lv_globals_0_0= ruleGlobalVariable ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) )
            {
            // InternalMyDsl.g:78:2: ( ( (lv_globals_0_0= ruleGlobalVariable ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) ) )
            // InternalMyDsl.g:79:3: ( (lv_globals_0_0= ruleGlobalVariable ) )* ( (lv_tasks_1_0= ruleTask ) )* ( (lv_entry_2_0= ruleEntryTask ) )
            {
            // InternalMyDsl.g:79:3: ( (lv_globals_0_0= ruleGlobalVariable ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==31) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalMyDsl.g:80:4: (lv_globals_0_0= ruleGlobalVariable )
            	    {
            	    // InternalMyDsl.g:80:4: (lv_globals_0_0= ruleGlobalVariable )
            	    // InternalMyDsl.g:81:5: lv_globals_0_0= ruleGlobalVariable
            	    {

            	    					newCompositeNode(grammarAccess.getInkAppAccess().getGlobalsGlobalVariableParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_globals_0_0=ruleGlobalVariable();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getInkAppRule());
            	    					}
            	    					add(
            	    						current,
            	    						"globals",
            	    						lv_globals_0_0,
            	    						"org.xtext.example.mydsl.MyDsl.GlobalVariable");
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


    // $ANTLR start "entryRuleBreak"
    // InternalMyDsl.g:258:1: entryRuleBreak returns [EObject current=null] : iv_ruleBreak= ruleBreak EOF ;
    public final EObject entryRuleBreak() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBreak = null;


        try {
            // InternalMyDsl.g:258:46: (iv_ruleBreak= ruleBreak EOF )
            // InternalMyDsl.g:259:2: iv_ruleBreak= ruleBreak EOF
            {
             newCompositeNode(grammarAccess.getBreakRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBreak=ruleBreak();

            state._fsp--;

             current =iv_ruleBreak; 
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
    // $ANTLR end "entryRuleBreak"


    // $ANTLR start "ruleBreak"
    // InternalMyDsl.g:265:1: ruleBreak returns [EObject current=null] : ( () ( (lv_break_1_0= 'break' ) ) ) ;
    public final EObject ruleBreak() throws RecognitionException {
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
            					grammarAccess.getBreakAccess().getBreakAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:280:3: ( (lv_break_1_0= 'break' ) )
            // InternalMyDsl.g:281:4: (lv_break_1_0= 'break' )
            {
            // InternalMyDsl.g:281:4: (lv_break_1_0= 'break' )
            // InternalMyDsl.g:282:5: lv_break_1_0= 'break'
            {
            lv_break_1_0=(Token)match(input,27,FOLLOW_2); 

            					newLeafNode(lv_break_1_0, grammarAccess.getBreakAccess().getBreakBreakKeyword_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getBreakRule());
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
    // $ANTLR end "ruleBreak"


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
    // InternalMyDsl.g:305:1: ruleVariableType returns [EObject current=null] : ( ( () otherlv_1= 'string' ) | ( () otherlv_3= 'integer' ) | ( () otherlv_5= 'boolean' ) ) ;
    public final EObject ruleVariableType() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;


        	enterRule();

        try {
            // InternalMyDsl.g:311:2: ( ( ( () otherlv_1= 'string' ) | ( () otherlv_3= 'integer' ) | ( () otherlv_5= 'boolean' ) ) )
            // InternalMyDsl.g:312:2: ( ( () otherlv_1= 'string' ) | ( () otherlv_3= 'integer' ) | ( () otherlv_5= 'boolean' ) )
            {
            // InternalMyDsl.g:312:2: ( ( () otherlv_1= 'string' ) | ( () otherlv_3= 'integer' ) | ( () otherlv_5= 'boolean' ) )
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
                    // InternalMyDsl.g:313:3: ( () otherlv_1= 'string' )
                    {
                    // InternalMyDsl.g:313:3: ( () otherlv_1= 'string' )
                    // InternalMyDsl.g:314:4: () otherlv_1= 'string'
                    {
                    // InternalMyDsl.g:314:4: ()
                    // InternalMyDsl.g:315:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getVariableTypeAccess().getVariableTypeAction_0_0(),
                    						current);
                    				

                    }

                    otherlv_1=(Token)match(input,28,FOLLOW_2); 

                    				newLeafNode(otherlv_1, grammarAccess.getVariableTypeAccess().getStringKeyword_0_1());
                    			

                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:327:3: ( () otherlv_3= 'integer' )
                    {
                    // InternalMyDsl.g:327:3: ( () otherlv_3= 'integer' )
                    // InternalMyDsl.g:328:4: () otherlv_3= 'integer'
                    {
                    // InternalMyDsl.g:328:4: ()
                    // InternalMyDsl.g:329:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getVariableTypeAccess().getVariableTypeAction_1_0(),
                    						current);
                    				

                    }

                    otherlv_3=(Token)match(input,29,FOLLOW_2); 

                    				newLeafNode(otherlv_3, grammarAccess.getVariableTypeAccess().getIntegerKeyword_1_1());
                    			

                    }


                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:341:3: ( () otherlv_5= 'boolean' )
                    {
                    // InternalMyDsl.g:341:3: ( () otherlv_5= 'boolean' )
                    // InternalMyDsl.g:342:4: () otherlv_5= 'boolean'
                    {
                    // InternalMyDsl.g:342:4: ()
                    // InternalMyDsl.g:343:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getVariableTypeAccess().getVariableTypeAction_2_0(),
                    						current);
                    				

                    }

                    otherlv_5=(Token)match(input,30,FOLLOW_2); 

                    				newLeafNode(otherlv_5, grammarAccess.getVariableTypeAccess().getBooleanKeyword_2_1());
                    			

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
    // $ANTLR end "ruleVariableType"


    // $ANTLR start "entryRuleGlobalVariable"
    // InternalMyDsl.g:358:1: entryRuleGlobalVariable returns [EObject current=null] : iv_ruleGlobalVariable= ruleGlobalVariable EOF ;
    public final EObject entryRuleGlobalVariable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleGlobalVariable = null;


        try {
            // InternalMyDsl.g:358:55: (iv_ruleGlobalVariable= ruleGlobalVariable EOF )
            // InternalMyDsl.g:359:2: iv_ruleGlobalVariable= ruleGlobalVariable EOF
            {
             newCompositeNode(grammarAccess.getGlobalVariableRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleGlobalVariable=ruleGlobalVariable();

            state._fsp--;

             current =iv_ruleGlobalVariable; 
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
    // $ANTLR end "entryRuleGlobalVariable"


    // $ANTLR start "ruleGlobalVariable"
    // InternalMyDsl.g:365:1: ruleGlobalVariable returns [EObject current=null] : ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDecleration ) ) ) ;
    public final EObject ruleGlobalVariable() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_variable_2_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:371:2: ( ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDecleration ) ) ) )
            // InternalMyDsl.g:372:2: ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDecleration ) ) )
            {
            // InternalMyDsl.g:372:2: ( () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDecleration ) ) )
            // InternalMyDsl.g:373:3: () otherlv_1= 'GLOBAL' ( (lv_variable_2_0= ruleVariableDecleration ) )
            {
            // InternalMyDsl.g:373:3: ()
            // InternalMyDsl.g:374:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getGlobalVariableAccess().getGlobalVariableAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,31,FOLLOW_5); 

            			newLeafNode(otherlv_1, grammarAccess.getGlobalVariableAccess().getGLOBALKeyword_1());
            		
            // InternalMyDsl.g:384:3: ( (lv_variable_2_0= ruleVariableDecleration ) )
            // InternalMyDsl.g:385:4: (lv_variable_2_0= ruleVariableDecleration )
            {
            // InternalMyDsl.g:385:4: (lv_variable_2_0= ruleVariableDecleration )
            // InternalMyDsl.g:386:5: lv_variable_2_0= ruleVariableDecleration
            {

            					newCompositeNode(grammarAccess.getGlobalVariableAccess().getVariableVariableDeclerationParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_variable_2_0=ruleVariableDecleration();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getGlobalVariableRule());
            					}
            					set(
            						current,
            						"variable",
            						lv_variable_2_0,
            						"org.xtext.example.mydsl.MyDsl.VariableDecleration");
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
    // $ANTLR end "ruleGlobalVariable"


    // $ANTLR start "entryRuleEntryTask"
    // InternalMyDsl.g:407:1: entryRuleEntryTask returns [EObject current=null] : iv_ruleEntryTask= ruleEntryTask EOF ;
    public final EObject entryRuleEntryTask() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleEntryTask = null;


        try {
            // InternalMyDsl.g:407:50: (iv_ruleEntryTask= ruleEntryTask EOF )
            // InternalMyDsl.g:408:2: iv_ruleEntryTask= ruleEntryTask EOF
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
    // InternalMyDsl.g:414:1: ruleEntryTask returns [EObject current=null] : (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) ) ;
    public final EObject ruleEntryTask() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        EObject lv_task_1_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:420:2: ( (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) ) )
            // InternalMyDsl.g:421:2: (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) )
            {
            // InternalMyDsl.g:421:2: (otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) ) )
            // InternalMyDsl.g:422:3: otherlv_0= 'entry' ( (lv_task_1_0= ruleTask ) )
            {
            otherlv_0=(Token)match(input,32,FOLLOW_6); 

            			newLeafNode(otherlv_0, grammarAccess.getEntryTaskAccess().getEntryKeyword_0());
            		
            // InternalMyDsl.g:426:3: ( (lv_task_1_0= ruleTask ) )
            // InternalMyDsl.g:427:4: (lv_task_1_0= ruleTask )
            {
            // InternalMyDsl.g:427:4: (lv_task_1_0= ruleTask )
            // InternalMyDsl.g:428:5: lv_task_1_0= ruleTask
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
    // InternalMyDsl.g:449:1: entryRuleTask returns [EObject current=null] : iv_ruleTask= ruleTask EOF ;
    public final EObject entryRuleTask() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTask = null;


        try {
            // InternalMyDsl.g:449:45: (iv_ruleTask= ruleTask EOF )
            // InternalMyDsl.g:450:2: iv_ruleTask= ruleTask EOF
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
    // InternalMyDsl.g:456:1: ruleTask returns [EObject current=null] : (otherlv_0= 'task' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_body_3_0= ruleVariableDecleration ) )* ( (lv_body_4_0= ruleExpression ) )* ( (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) ) | otherlv_7= 'end' ) otherlv_8= '}' ) ;
    public final EObject ruleTask() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token otherlv_2=null;
        Token otherlv_5=null;
        Token otherlv_6=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        EObject lv_body_3_0 = null;

        EObject lv_body_4_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:462:2: ( (otherlv_0= 'task' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_body_3_0= ruleVariableDecleration ) )* ( (lv_body_4_0= ruleExpression ) )* ( (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) ) | otherlv_7= 'end' ) otherlv_8= '}' ) )
            // InternalMyDsl.g:463:2: (otherlv_0= 'task' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_body_3_0= ruleVariableDecleration ) )* ( (lv_body_4_0= ruleExpression ) )* ( (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) ) | otherlv_7= 'end' ) otherlv_8= '}' )
            {
            // InternalMyDsl.g:463:2: (otherlv_0= 'task' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_body_3_0= ruleVariableDecleration ) )* ( (lv_body_4_0= ruleExpression ) )* ( (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) ) | otherlv_7= 'end' ) otherlv_8= '}' )
            // InternalMyDsl.g:464:3: otherlv_0= 'task' ( (lv_name_1_0= RULE_ID ) ) otherlv_2= '{' ( (lv_body_3_0= ruleVariableDecleration ) )* ( (lv_body_4_0= ruleExpression ) )* ( (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) ) | otherlv_7= 'end' ) otherlv_8= '}'
            {
            otherlv_0=(Token)match(input,33,FOLLOW_7); 

            			newLeafNode(otherlv_0, grammarAccess.getTaskAccess().getTaskKeyword_0());
            		
            // InternalMyDsl.g:468:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:469:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:469:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:470:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_8); 

            					newLeafNode(lv_name_1_0, grammarAccess.getTaskAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getTaskRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

            }


            }

            otherlv_2=(Token)match(input,34,FOLLOW_9); 

            			newLeafNode(otherlv_2, grammarAccess.getTaskAccess().getLeftCurlyBracketKeyword_2());
            		
            // InternalMyDsl.g:490:3: ( (lv_body_3_0= ruleVariableDecleration ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>=28 && LA5_0<=30)) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalMyDsl.g:491:4: (lv_body_3_0= ruleVariableDecleration )
            	    {
            	    // InternalMyDsl.g:491:4: (lv_body_3_0= ruleVariableDecleration )
            	    // InternalMyDsl.g:492:5: lv_body_3_0= ruleVariableDecleration
            	    {

            	    					newCompositeNode(grammarAccess.getTaskAccess().getBodyVariableDeclerationParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_body_3_0=ruleVariableDecleration();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTaskRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_3_0,
            	    						"org.xtext.example.mydsl.MyDsl.VariableDecleration");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalMyDsl.g:509:3: ( (lv_body_4_0= ruleExpression ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=RULE_ID && LA6_0<=RULE_BOOLEAN)||LA6_0==26||LA6_0==38||LA6_0==42||LA6_0==44) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalMyDsl.g:510:4: (lv_body_4_0= ruleExpression )
            	    {
            	    // InternalMyDsl.g:510:4: (lv_body_4_0= ruleExpression )
            	    // InternalMyDsl.g:511:5: lv_body_4_0= ruleExpression
            	    {

            	    					newCompositeNode(grammarAccess.getTaskAccess().getBodyExpressionParserRuleCall_4_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_body_4_0=ruleExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getTaskRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_4_0,
            	    						"org.xtext.example.mydsl.MyDsl.Expression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // InternalMyDsl.g:528:3: ( (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) ) | otherlv_7= 'end' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==35) ) {
                alt7=1;
            }
            else if ( (LA7_0==36) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // InternalMyDsl.g:529:4: (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) )
                    {
                    // InternalMyDsl.g:529:4: (otherlv_5= 'next' ( (otherlv_6= RULE_ID ) ) )
                    // InternalMyDsl.g:530:5: otherlv_5= 'next' ( (otherlv_6= RULE_ID ) )
                    {
                    otherlv_5=(Token)match(input,35,FOLLOW_7); 

                    					newLeafNode(otherlv_5, grammarAccess.getTaskAccess().getNextKeyword_5_0_0());
                    				
                    // InternalMyDsl.g:534:5: ( (otherlv_6= RULE_ID ) )
                    // InternalMyDsl.g:535:6: (otherlv_6= RULE_ID )
                    {
                    // InternalMyDsl.g:535:6: (otherlv_6= RULE_ID )
                    // InternalMyDsl.g:536:7: otherlv_6= RULE_ID
                    {

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getTaskRule());
                    							}
                    						
                    otherlv_6=(Token)match(input,RULE_ID,FOLLOW_11); 

                    							newLeafNode(otherlv_6, grammarAccess.getTaskAccess().getTaskTaskCrossReference_5_0_1_0());
                    						

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:549:4: otherlv_7= 'end'
                    {
                    otherlv_7=(Token)match(input,36,FOLLOW_11); 

                    				newLeafNode(otherlv_7, grammarAccess.getTaskAccess().getEndKeyword_5_1());
                    			

                    }
                    break;

            }

            otherlv_8=(Token)match(input,37,FOLLOW_2); 

            			newLeafNode(otherlv_8, grammarAccess.getTaskAccess().getRightCurlyBracketKeyword_6());
            		

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
    // InternalMyDsl.g:562:1: entryRuleIfExpression returns [EObject current=null] : iv_ruleIfExpression= ruleIfExpression EOF ;
    public final EObject entryRuleIfExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIfExpression = null;


        try {
            // InternalMyDsl.g:562:53: (iv_ruleIfExpression= ruleIfExpression EOF )
            // InternalMyDsl.g:563:2: iv_ruleIfExpression= ruleIfExpression EOF
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
    // InternalMyDsl.g:569:1: ruleIfExpression returns [EObject current=null] : (otherlv_0= 'if' otherlv_1= '(' ( (lv_ifcondition_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_ifbody_5_0= ruleExpression ) )* ( (lv_ifbody_6_0= ruleBreak ) )? otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}' )* (otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}' )? ) ;
    public final EObject ruleIfExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_9=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token otherlv_13=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_18=null;
        Token otherlv_21=null;
        EObject lv_ifcondition_2_0 = null;

        EObject lv_ifbody_5_0 = null;

        EObject lv_ifbody_6_0 = null;

        EObject lv_elsecondition_11_0 = null;

        EObject lv_elseifbody_14_0 = null;

        EObject lv_elseifbody_15_0 = null;

        EObject lv_elsebody_19_0 = null;

        EObject lv_elseifbody_20_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:575:2: ( (otherlv_0= 'if' otherlv_1= '(' ( (lv_ifcondition_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_ifbody_5_0= ruleExpression ) )* ( (lv_ifbody_6_0= ruleBreak ) )? otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}' )* (otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}' )? ) )
            // InternalMyDsl.g:576:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_ifcondition_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_ifbody_5_0= ruleExpression ) )* ( (lv_ifbody_6_0= ruleBreak ) )? otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}' )* (otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}' )? )
            {
            // InternalMyDsl.g:576:2: (otherlv_0= 'if' otherlv_1= '(' ( (lv_ifcondition_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_ifbody_5_0= ruleExpression ) )* ( (lv_ifbody_6_0= ruleBreak ) )? otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}' )* (otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}' )? )
            // InternalMyDsl.g:577:3: otherlv_0= 'if' otherlv_1= '(' ( (lv_ifcondition_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_ifbody_5_0= ruleExpression ) )* ( (lv_ifbody_6_0= ruleBreak ) )? otherlv_7= '}' (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}' )* (otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}' )?
            {
            otherlv_0=(Token)match(input,38,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getIfExpressionAccess().getIfKeyword_0());
            		
            otherlv_1=(Token)match(input,39,FOLLOW_13); 

            			newLeafNode(otherlv_1, grammarAccess.getIfExpressionAccess().getLeftParenthesisKeyword_1());
            		
            // InternalMyDsl.g:585:3: ( (lv_ifcondition_2_0= ruleOperationExpression ) )
            // InternalMyDsl.g:586:4: (lv_ifcondition_2_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:586:4: (lv_ifcondition_2_0= ruleOperationExpression )
            // InternalMyDsl.g:587:5: lv_ifcondition_2_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getIfExpressionAccess().getIfconditionOperationExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_14);
            lv_ifcondition_2_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            					}
            					set(
            						current,
            						"ifcondition",
            						lv_ifcondition_2_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,40,FOLLOW_8); 

            			newLeafNode(otherlv_3, grammarAccess.getIfExpressionAccess().getRightParenthesisKeyword_3());
            		
            otherlv_4=(Token)match(input,34,FOLLOW_15); 

            			newLeafNode(otherlv_4, grammarAccess.getIfExpressionAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMyDsl.g:612:3: ( (lv_ifbody_5_0= ruleExpression ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>=RULE_ID && LA8_0<=RULE_BOOLEAN)||LA8_0==26||LA8_0==38||LA8_0==42||LA8_0==44) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalMyDsl.g:613:4: (lv_ifbody_5_0= ruleExpression )
            	    {
            	    // InternalMyDsl.g:613:4: (lv_ifbody_5_0= ruleExpression )
            	    // InternalMyDsl.g:614:5: lv_ifbody_5_0= ruleExpression
            	    {

            	    					newCompositeNode(grammarAccess.getIfExpressionAccess().getIfbodyExpressionParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_15);
            	    lv_ifbody_5_0=ruleExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"ifbody",
            	    						lv_ifbody_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.Expression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalMyDsl.g:631:3: ( (lv_ifbody_6_0= ruleBreak ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==27) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // InternalMyDsl.g:632:4: (lv_ifbody_6_0= ruleBreak )
                    {
                    // InternalMyDsl.g:632:4: (lv_ifbody_6_0= ruleBreak )
                    // InternalMyDsl.g:633:5: lv_ifbody_6_0= ruleBreak
                    {

                    					newCompositeNode(grammarAccess.getIfExpressionAccess().getIfbodyBreakParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_ifbody_6_0=ruleBreak();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getIfExpressionRule());
                    					}
                    					add(
                    						current,
                    						"ifbody",
                    						lv_ifbody_6_0,
                    						"org.xtext.example.mydsl.MyDsl.Break");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,37,FOLLOW_16); 

            			newLeafNode(otherlv_7, grammarAccess.getIfExpressionAccess().getRightCurlyBracketKeyword_7());
            		
            // InternalMyDsl.g:654:3: (otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==41) ) {
                    int LA12_1 = input.LA(2);

                    if ( (LA12_1==38) ) {
                        alt12=1;
                    }


                }


                switch (alt12) {
            	case 1 :
            	    // InternalMyDsl.g:655:4: otherlv_8= 'else' otherlv_9= 'if' otherlv_10= '(' ( (lv_elsecondition_11_0= ruleOperationExpression ) ) otherlv_12= ')' otherlv_13= '{' ( (lv_elseifbody_14_0= ruleExpression ) )* ( (lv_elseifbody_15_0= ruleBreak ) )? otherlv_16= '}'
            	    {
            	    otherlv_8=(Token)match(input,41,FOLLOW_17); 

            	    				newLeafNode(otherlv_8, grammarAccess.getIfExpressionAccess().getElseKeyword_8_0());
            	    			
            	    otherlv_9=(Token)match(input,38,FOLLOW_12); 

            	    				newLeafNode(otherlv_9, grammarAccess.getIfExpressionAccess().getIfKeyword_8_1());
            	    			
            	    otherlv_10=(Token)match(input,39,FOLLOW_13); 

            	    				newLeafNode(otherlv_10, grammarAccess.getIfExpressionAccess().getLeftParenthesisKeyword_8_2());
            	    			
            	    // InternalMyDsl.g:667:4: ( (lv_elsecondition_11_0= ruleOperationExpression ) )
            	    // InternalMyDsl.g:668:5: (lv_elsecondition_11_0= ruleOperationExpression )
            	    {
            	    // InternalMyDsl.g:668:5: (lv_elsecondition_11_0= ruleOperationExpression )
            	    // InternalMyDsl.g:669:6: lv_elsecondition_11_0= ruleOperationExpression
            	    {

            	    						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseconditionOperationExpressionParserRuleCall_8_3_0());
            	    					
            	    pushFollow(FOLLOW_14);
            	    lv_elsecondition_11_0=ruleOperationExpression();

            	    state._fsp--;


            	    						if (current==null) {
            	    							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            	    						}
            	    						add(
            	    							current,
            	    							"elsecondition",
            	    							lv_elsecondition_11_0,
            	    							"org.xtext.example.mydsl.MyDsl.OperationExpression");
            	    						afterParserOrEnumRuleCall();
            	    					

            	    }


            	    }

            	    otherlv_12=(Token)match(input,40,FOLLOW_8); 

            	    				newLeafNode(otherlv_12, grammarAccess.getIfExpressionAccess().getRightParenthesisKeyword_8_4());
            	    			
            	    otherlv_13=(Token)match(input,34,FOLLOW_15); 

            	    				newLeafNode(otherlv_13, grammarAccess.getIfExpressionAccess().getLeftCurlyBracketKeyword_8_5());
            	    			
            	    // InternalMyDsl.g:694:4: ( (lv_elseifbody_14_0= ruleExpression ) )*
            	    loop10:
            	    do {
            	        int alt10=2;
            	        int LA10_0 = input.LA(1);

            	        if ( ((LA10_0>=RULE_ID && LA10_0<=RULE_BOOLEAN)||LA10_0==26||LA10_0==38||LA10_0==42||LA10_0==44) ) {
            	            alt10=1;
            	        }


            	        switch (alt10) {
            	    	case 1 :
            	    	    // InternalMyDsl.g:695:5: (lv_elseifbody_14_0= ruleExpression )
            	    	    {
            	    	    // InternalMyDsl.g:695:5: (lv_elseifbody_14_0= ruleExpression )
            	    	    // InternalMyDsl.g:696:6: lv_elseifbody_14_0= ruleExpression
            	    	    {

            	    	    						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseifbodyExpressionParserRuleCall_8_6_0());
            	    	    					
            	    	    pushFollow(FOLLOW_15);
            	    	    lv_elseifbody_14_0=ruleExpression();

            	    	    state._fsp--;


            	    	    						if (current==null) {
            	    	    							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            	    	    						}
            	    	    						add(
            	    	    							current,
            	    	    							"elseifbody",
            	    	    							lv_elseifbody_14_0,
            	    	    							"org.xtext.example.mydsl.MyDsl.Expression");
            	    	    						afterParserOrEnumRuleCall();
            	    	    					

            	    	    }


            	    	    }
            	    	    break;

            	    	default :
            	    	    break loop10;
            	        }
            	    } while (true);

            	    // InternalMyDsl.g:713:4: ( (lv_elseifbody_15_0= ruleBreak ) )?
            	    int alt11=2;
            	    int LA11_0 = input.LA(1);

            	    if ( (LA11_0==27) ) {
            	        alt11=1;
            	    }
            	    switch (alt11) {
            	        case 1 :
            	            // InternalMyDsl.g:714:5: (lv_elseifbody_15_0= ruleBreak )
            	            {
            	            // InternalMyDsl.g:714:5: (lv_elseifbody_15_0= ruleBreak )
            	            // InternalMyDsl.g:715:6: lv_elseifbody_15_0= ruleBreak
            	            {

            	            						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseifbodyBreakParserRuleCall_8_7_0());
            	            					
            	            pushFollow(FOLLOW_11);
            	            lv_elseifbody_15_0=ruleBreak();

            	            state._fsp--;


            	            						if (current==null) {
            	            							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
            	            						}
            	            						add(
            	            							current,
            	            							"elseifbody",
            	            							lv_elseifbody_15_0,
            	            							"org.xtext.example.mydsl.MyDsl.Break");
            	            						afterParserOrEnumRuleCall();
            	            					

            	            }


            	            }
            	            break;

            	    }

            	    otherlv_16=(Token)match(input,37,FOLLOW_16); 

            	    				newLeafNode(otherlv_16, grammarAccess.getIfExpressionAccess().getRightCurlyBracketKeyword_8_8());
            	    			

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

            // InternalMyDsl.g:737:3: (otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}' )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==41) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // InternalMyDsl.g:738:4: otherlv_17= 'else' otherlv_18= '{' ( (lv_elsebody_19_0= ruleExpression ) )* ( (lv_elseifbody_20_0= ruleBreak ) )? otherlv_21= '}'
                    {
                    otherlv_17=(Token)match(input,41,FOLLOW_8); 

                    				newLeafNode(otherlv_17, grammarAccess.getIfExpressionAccess().getElseKeyword_9_0());
                    			
                    otherlv_18=(Token)match(input,34,FOLLOW_15); 

                    				newLeafNode(otherlv_18, grammarAccess.getIfExpressionAccess().getLeftCurlyBracketKeyword_9_1());
                    			
                    // InternalMyDsl.g:746:4: ( (lv_elsebody_19_0= ruleExpression ) )*
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( ((LA13_0>=RULE_ID && LA13_0<=RULE_BOOLEAN)||LA13_0==26||LA13_0==38||LA13_0==42||LA13_0==44) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // InternalMyDsl.g:747:5: (lv_elsebody_19_0= ruleExpression )
                    	    {
                    	    // InternalMyDsl.g:747:5: (lv_elsebody_19_0= ruleExpression )
                    	    // InternalMyDsl.g:748:6: lv_elsebody_19_0= ruleExpression
                    	    {

                    	    						newCompositeNode(grammarAccess.getIfExpressionAccess().getElsebodyExpressionParserRuleCall_9_2_0());
                    	    					
                    	    pushFollow(FOLLOW_15);
                    	    lv_elsebody_19_0=ruleExpression();

                    	    state._fsp--;


                    	    						if (current==null) {
                    	    							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
                    	    						}
                    	    						add(
                    	    							current,
                    	    							"elsebody",
                    	    							lv_elsebody_19_0,
                    	    							"org.xtext.example.mydsl.MyDsl.Expression");
                    	    						afterParserOrEnumRuleCall();
                    	    					

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop13;
                        }
                    } while (true);

                    // InternalMyDsl.g:765:4: ( (lv_elseifbody_20_0= ruleBreak ) )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==27) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // InternalMyDsl.g:766:5: (lv_elseifbody_20_0= ruleBreak )
                            {
                            // InternalMyDsl.g:766:5: (lv_elseifbody_20_0= ruleBreak )
                            // InternalMyDsl.g:767:6: lv_elseifbody_20_0= ruleBreak
                            {

                            						newCompositeNode(grammarAccess.getIfExpressionAccess().getElseifbodyBreakParserRuleCall_9_3_0());
                            					
                            pushFollow(FOLLOW_11);
                            lv_elseifbody_20_0=ruleBreak();

                            state._fsp--;


                            						if (current==null) {
                            							current = createModelElementForParent(grammarAccess.getIfExpressionRule());
                            						}
                            						add(
                            							current,
                            							"elseifbody",
                            							lv_elseifbody_20_0,
                            							"org.xtext.example.mydsl.MyDsl.Break");
                            						afterParserOrEnumRuleCall();
                            					

                            }


                            }
                            break;

                    }

                    otherlv_21=(Token)match(input,37,FOLLOW_2); 

                    				newLeafNode(otherlv_21, grammarAccess.getIfExpressionAccess().getRightCurlyBracketKeyword_9_4());
                    			

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
    // InternalMyDsl.g:793:1: entryRuleForExpression returns [EObject current=null] : iv_ruleForExpression= ruleForExpression EOF ;
    public final EObject entryRuleForExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleForExpression = null;


        try {
            // InternalMyDsl.g:793:54: (iv_ruleForExpression= ruleForExpression EOF )
            // InternalMyDsl.g:794:2: iv_ruleForExpression= ruleForExpression EOF
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
    // InternalMyDsl.g:800:1: ruleForExpression returns [EObject current=null] : (otherlv_0= 'for' otherlv_1= '(' ( (lv_initial_2_0= ruleOperationExpression ) ) otherlv_3= ';' ( (lv_test_4_0= ruleOperationExpression ) ) otherlv_5= ';' ( (lv_update_6_0= ruleOperationExpression ) ) otherlv_7= ')' otherlv_8= '{' ( (lv_body_9_0= ruleExpression ) )* ( (lv_body_10_0= ruleBreak ) )? otherlv_11= '}' ) ;
    public final EObject ruleForExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        Token otherlv_7=null;
        Token otherlv_8=null;
        Token otherlv_11=null;
        EObject lv_initial_2_0 = null;

        EObject lv_test_4_0 = null;

        EObject lv_update_6_0 = null;

        EObject lv_body_9_0 = null;

        EObject lv_body_10_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:806:2: ( (otherlv_0= 'for' otherlv_1= '(' ( (lv_initial_2_0= ruleOperationExpression ) ) otherlv_3= ';' ( (lv_test_4_0= ruleOperationExpression ) ) otherlv_5= ';' ( (lv_update_6_0= ruleOperationExpression ) ) otherlv_7= ')' otherlv_8= '{' ( (lv_body_9_0= ruleExpression ) )* ( (lv_body_10_0= ruleBreak ) )? otherlv_11= '}' ) )
            // InternalMyDsl.g:807:2: (otherlv_0= 'for' otherlv_1= '(' ( (lv_initial_2_0= ruleOperationExpression ) ) otherlv_3= ';' ( (lv_test_4_0= ruleOperationExpression ) ) otherlv_5= ';' ( (lv_update_6_0= ruleOperationExpression ) ) otherlv_7= ')' otherlv_8= '{' ( (lv_body_9_0= ruleExpression ) )* ( (lv_body_10_0= ruleBreak ) )? otherlv_11= '}' )
            {
            // InternalMyDsl.g:807:2: (otherlv_0= 'for' otherlv_1= '(' ( (lv_initial_2_0= ruleOperationExpression ) ) otherlv_3= ';' ( (lv_test_4_0= ruleOperationExpression ) ) otherlv_5= ';' ( (lv_update_6_0= ruleOperationExpression ) ) otherlv_7= ')' otherlv_8= '{' ( (lv_body_9_0= ruleExpression ) )* ( (lv_body_10_0= ruleBreak ) )? otherlv_11= '}' )
            // InternalMyDsl.g:808:3: otherlv_0= 'for' otherlv_1= '(' ( (lv_initial_2_0= ruleOperationExpression ) ) otherlv_3= ';' ( (lv_test_4_0= ruleOperationExpression ) ) otherlv_5= ';' ( (lv_update_6_0= ruleOperationExpression ) ) otherlv_7= ')' otherlv_8= '{' ( (lv_body_9_0= ruleExpression ) )* ( (lv_body_10_0= ruleBreak ) )? otherlv_11= '}'
            {
            otherlv_0=(Token)match(input,42,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getForExpressionAccess().getForKeyword_0());
            		
            otherlv_1=(Token)match(input,39,FOLLOW_13); 

            			newLeafNode(otherlv_1, grammarAccess.getForExpressionAccess().getLeftParenthesisKeyword_1());
            		
            // InternalMyDsl.g:816:3: ( (lv_initial_2_0= ruleOperationExpression ) )
            // InternalMyDsl.g:817:4: (lv_initial_2_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:817:4: (lv_initial_2_0= ruleOperationExpression )
            // InternalMyDsl.g:818:5: lv_initial_2_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getInitialOperationExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_18);
            lv_initial_2_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"initial",
            						lv_initial_2_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,43,FOLLOW_13); 

            			newLeafNode(otherlv_3, grammarAccess.getForExpressionAccess().getSemicolonKeyword_3());
            		
            // InternalMyDsl.g:839:3: ( (lv_test_4_0= ruleOperationExpression ) )
            // InternalMyDsl.g:840:4: (lv_test_4_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:840:4: (lv_test_4_0= ruleOperationExpression )
            // InternalMyDsl.g:841:5: lv_test_4_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getTestOperationExpressionParserRuleCall_4_0());
            				
            pushFollow(FOLLOW_18);
            lv_test_4_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"test",
            						lv_test_4_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_5=(Token)match(input,43,FOLLOW_13); 

            			newLeafNode(otherlv_5, grammarAccess.getForExpressionAccess().getSemicolonKeyword_5());
            		
            // InternalMyDsl.g:862:3: ( (lv_update_6_0= ruleOperationExpression ) )
            // InternalMyDsl.g:863:4: (lv_update_6_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:863:4: (lv_update_6_0= ruleOperationExpression )
            // InternalMyDsl.g:864:5: lv_update_6_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getForExpressionAccess().getUpdateOperationExpressionParserRuleCall_6_0());
            				
            pushFollow(FOLLOW_14);
            lv_update_6_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            					}
            					set(
            						current,
            						"update",
            						lv_update_6_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_7=(Token)match(input,40,FOLLOW_8); 

            			newLeafNode(otherlv_7, grammarAccess.getForExpressionAccess().getRightParenthesisKeyword_7());
            		
            otherlv_8=(Token)match(input,34,FOLLOW_15); 

            			newLeafNode(otherlv_8, grammarAccess.getForExpressionAccess().getLeftCurlyBracketKeyword_8());
            		
            // InternalMyDsl.g:889:3: ( (lv_body_9_0= ruleExpression ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( ((LA16_0>=RULE_ID && LA16_0<=RULE_BOOLEAN)||LA16_0==26||LA16_0==38||LA16_0==42||LA16_0==44) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // InternalMyDsl.g:890:4: (lv_body_9_0= ruleExpression )
            	    {
            	    // InternalMyDsl.g:890:4: (lv_body_9_0= ruleExpression )
            	    // InternalMyDsl.g:891:5: lv_body_9_0= ruleExpression
            	    {

            	    					newCompositeNode(grammarAccess.getForExpressionAccess().getBodyExpressionParserRuleCall_9_0());
            	    				
            	    pushFollow(FOLLOW_15);
            	    lv_body_9_0=ruleExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getForExpressionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_9_0,
            	    						"org.xtext.example.mydsl.MyDsl.Expression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);

            // InternalMyDsl.g:908:3: ( (lv_body_10_0= ruleBreak ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==27) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // InternalMyDsl.g:909:4: (lv_body_10_0= ruleBreak )
                    {
                    // InternalMyDsl.g:909:4: (lv_body_10_0= ruleBreak )
                    // InternalMyDsl.g:910:5: lv_body_10_0= ruleBreak
                    {

                    					newCompositeNode(grammarAccess.getForExpressionAccess().getBodyBreakParserRuleCall_10_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_body_10_0=ruleBreak();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getForExpressionRule());
                    					}
                    					add(
                    						current,
                    						"body",
                    						lv_body_10_0,
                    						"org.xtext.example.mydsl.MyDsl.Break");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_11=(Token)match(input,37,FOLLOW_2); 

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
    // InternalMyDsl.g:935:1: entryRuleWhileExpression returns [EObject current=null] : iv_ruleWhileExpression= ruleWhileExpression EOF ;
    public final EObject entryRuleWhileExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleWhileExpression = null;


        try {
            // InternalMyDsl.g:935:56: (iv_ruleWhileExpression= ruleWhileExpression EOF )
            // InternalMyDsl.g:936:2: iv_ruleWhileExpression= ruleWhileExpression EOF
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
    // InternalMyDsl.g:942:1: ruleWhileExpression returns [EObject current=null] : (otherlv_0= 'while' otherlv_1= '(' ( (lv_test_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_body_5_0= ruleExpression ) )* ( (lv_body_6_0= ruleBreak ) )? otherlv_7= '}' ) ;
    public final EObject ruleWhileExpression() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        Token otherlv_7=null;
        EObject lv_test_2_0 = null;

        EObject lv_body_5_0 = null;

        EObject lv_body_6_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:948:2: ( (otherlv_0= 'while' otherlv_1= '(' ( (lv_test_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_body_5_0= ruleExpression ) )* ( (lv_body_6_0= ruleBreak ) )? otherlv_7= '}' ) )
            // InternalMyDsl.g:949:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_test_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_body_5_0= ruleExpression ) )* ( (lv_body_6_0= ruleBreak ) )? otherlv_7= '}' )
            {
            // InternalMyDsl.g:949:2: (otherlv_0= 'while' otherlv_1= '(' ( (lv_test_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_body_5_0= ruleExpression ) )* ( (lv_body_6_0= ruleBreak ) )? otherlv_7= '}' )
            // InternalMyDsl.g:950:3: otherlv_0= 'while' otherlv_1= '(' ( (lv_test_2_0= ruleOperationExpression ) ) otherlv_3= ')' otherlv_4= '{' ( (lv_body_5_0= ruleExpression ) )* ( (lv_body_6_0= ruleBreak ) )? otherlv_7= '}'
            {
            otherlv_0=(Token)match(input,44,FOLLOW_12); 

            			newLeafNode(otherlv_0, grammarAccess.getWhileExpressionAccess().getWhileKeyword_0());
            		
            otherlv_1=(Token)match(input,39,FOLLOW_13); 

            			newLeafNode(otherlv_1, grammarAccess.getWhileExpressionAccess().getLeftParenthesisKeyword_1());
            		
            // InternalMyDsl.g:958:3: ( (lv_test_2_0= ruleOperationExpression ) )
            // InternalMyDsl.g:959:4: (lv_test_2_0= ruleOperationExpression )
            {
            // InternalMyDsl.g:959:4: (lv_test_2_0= ruleOperationExpression )
            // InternalMyDsl.g:960:5: lv_test_2_0= ruleOperationExpression
            {

            					newCompositeNode(grammarAccess.getWhileExpressionAccess().getTestOperationExpressionParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_14);
            lv_test_2_0=ruleOperationExpression();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
            					}
            					set(
            						current,
            						"test",
            						lv_test_2_0,
            						"org.xtext.example.mydsl.MyDsl.OperationExpression");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_3=(Token)match(input,40,FOLLOW_8); 

            			newLeafNode(otherlv_3, grammarAccess.getWhileExpressionAccess().getRightParenthesisKeyword_3());
            		
            otherlv_4=(Token)match(input,34,FOLLOW_15); 

            			newLeafNode(otherlv_4, grammarAccess.getWhileExpressionAccess().getLeftCurlyBracketKeyword_4());
            		
            // InternalMyDsl.g:985:3: ( (lv_body_5_0= ruleExpression ) )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( ((LA18_0>=RULE_ID && LA18_0<=RULE_BOOLEAN)||LA18_0==26||LA18_0==38||LA18_0==42||LA18_0==44) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // InternalMyDsl.g:986:4: (lv_body_5_0= ruleExpression )
            	    {
            	    // InternalMyDsl.g:986:4: (lv_body_5_0= ruleExpression )
            	    // InternalMyDsl.g:987:5: lv_body_5_0= ruleExpression
            	    {

            	    					newCompositeNode(grammarAccess.getWhileExpressionAccess().getBodyExpressionParserRuleCall_5_0());
            	    				
            	    pushFollow(FOLLOW_15);
            	    lv_body_5_0=ruleExpression();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
            	    					}
            	    					add(
            	    						current,
            	    						"body",
            	    						lv_body_5_0,
            	    						"org.xtext.example.mydsl.MyDsl.Expression");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);

            // InternalMyDsl.g:1004:3: ( (lv_body_6_0= ruleBreak ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==27) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // InternalMyDsl.g:1005:4: (lv_body_6_0= ruleBreak )
                    {
                    // InternalMyDsl.g:1005:4: (lv_body_6_0= ruleBreak )
                    // InternalMyDsl.g:1006:5: lv_body_6_0= ruleBreak
                    {

                    					newCompositeNode(grammarAccess.getWhileExpressionAccess().getBodyBreakParserRuleCall_6_0());
                    				
                    pushFollow(FOLLOW_11);
                    lv_body_6_0=ruleBreak();

                    state._fsp--;


                    					if (current==null) {
                    						current = createModelElementForParent(grammarAccess.getWhileExpressionRule());
                    					}
                    					add(
                    						current,
                    						"body",
                    						lv_body_6_0,
                    						"org.xtext.example.mydsl.MyDsl.Break");
                    					afterParserOrEnumRuleCall();
                    				

                    }


                    }
                    break;

            }

            otherlv_7=(Token)match(input,37,FOLLOW_2); 

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


    // $ANTLR start "entryRuleExpression"
    // InternalMyDsl.g:1031:1: entryRuleExpression returns [EObject current=null] : iv_ruleExpression= ruleExpression EOF ;
    public final EObject entryRuleExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression = null;


        try {
            // InternalMyDsl.g:1031:51: (iv_ruleExpression= ruleExpression EOF )
            // InternalMyDsl.g:1032:2: iv_ruleExpression= ruleExpression EOF
            {
             newCompositeNode(grammarAccess.getExpressionRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleExpression=ruleExpression();

            state._fsp--;

             current =iv_ruleExpression; 
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
    // $ANTLR end "entryRuleExpression"


    // $ANTLR start "ruleExpression"
    // InternalMyDsl.g:1038:1: ruleExpression returns [EObject current=null] : (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCall_4= ruleBuiltinFunctionCall ) ;
    public final EObject ruleExpression() throws RecognitionException {
        EObject current = null;

        EObject this_OperationExpression_0 = null;

        EObject this_IfExpression_1 = null;

        EObject this_ForExpression_2 = null;

        EObject this_WhileExpression_3 = null;

        EObject this_BuiltinFunctionCall_4 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1044:2: ( (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCall_4= ruleBuiltinFunctionCall ) )
            // InternalMyDsl.g:1045:2: (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCall_4= ruleBuiltinFunctionCall )
            {
            // InternalMyDsl.g:1045:2: (this_OperationExpression_0= ruleOperationExpression | this_IfExpression_1= ruleIfExpression | this_ForExpression_2= ruleForExpression | this_WhileExpression_3= ruleWhileExpression | this_BuiltinFunctionCall_4= ruleBuiltinFunctionCall )
            int alt20=5;
            switch ( input.LA(1) ) {
            case RULE_ID:
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case RULE_BOOLEAN:
                {
                alt20=1;
                }
                break;
            case 38:
                {
                alt20=2;
                }
                break;
            case 42:
                {
                alt20=3;
                }
                break;
            case 44:
                {
                alt20=4;
                }
                break;
            case 26:
                {
                alt20=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;
            }

            switch (alt20) {
                case 1 :
                    // InternalMyDsl.g:1046:3: this_OperationExpression_0= ruleOperationExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getOperationExpressionParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_OperationExpression_0=ruleOperationExpression();

                    state._fsp--;


                    			current = this_OperationExpression_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1055:3: this_IfExpression_1= ruleIfExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getIfExpressionParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_IfExpression_1=ruleIfExpression();

                    state._fsp--;


                    			current = this_IfExpression_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalMyDsl.g:1064:3: this_ForExpression_2= ruleForExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getForExpressionParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_ForExpression_2=ruleForExpression();

                    state._fsp--;


                    			current = this_ForExpression_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalMyDsl.g:1073:3: this_WhileExpression_3= ruleWhileExpression
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getWhileExpressionParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_WhileExpression_3=ruleWhileExpression();

                    state._fsp--;


                    			current = this_WhileExpression_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 5 :
                    // InternalMyDsl.g:1082:3: this_BuiltinFunctionCall_4= ruleBuiltinFunctionCall
                    {

                    			newCompositeNode(grammarAccess.getExpressionAccess().getBuiltinFunctionCallParserRuleCall_4());
                    		
                    pushFollow(FOLLOW_2);
                    this_BuiltinFunctionCall_4=ruleBuiltinFunctionCall();

                    state._fsp--;


                    			current = this_BuiltinFunctionCall_4;
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
    // $ANTLR end "ruleExpression"


    // $ANTLR start "entryRuleOperationExpression"
    // InternalMyDsl.g:1094:1: entryRuleOperationExpression returns [EObject current=null] : iv_ruleOperationExpression= ruleOperationExpression EOF ;
    public final EObject entryRuleOperationExpression() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperationExpression = null;


        try {
            // InternalMyDsl.g:1094:60: (iv_ruleOperationExpression= ruleOperationExpression EOF )
            // InternalMyDsl.g:1095:2: iv_ruleOperationExpression= ruleOperationExpression EOF
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
    // InternalMyDsl.g:1101:1: ruleOperationExpression returns [EObject current=null] : this_Operation_0= ruleOperation ;
    public final EObject ruleOperationExpression() throws RecognitionException {
        EObject current = null;

        EObject this_Operation_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1107:2: (this_Operation_0= ruleOperation )
            // InternalMyDsl.g:1108:2: this_Operation_0= ruleOperation
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
    // InternalMyDsl.g:1119:1: entryRuleOperation returns [EObject current=null] : iv_ruleOperation= ruleOperation EOF ;
    public final EObject entryRuleOperation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOperation = null;


        try {
            // InternalMyDsl.g:1119:50: (iv_ruleOperation= ruleOperation EOF )
            // InternalMyDsl.g:1120:2: iv_ruleOperation= ruleOperation EOF
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
    // InternalMyDsl.g:1126:1: ruleOperation returns [EObject current=null] : (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )* ) ) ;
    public final EObject ruleOperation() throws RecognitionException {
        EObject current = null;

        EObject this_Atomic_0 = null;

        AntlrDatatypeRuleToken lv_operator_2_0 = null;

        EObject lv_right_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1132:2: ( (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )* ) ) )
            // InternalMyDsl.g:1133:2: (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )* ) )
            {
            // InternalMyDsl.g:1133:2: (this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )* ) )
            // InternalMyDsl.g:1134:3: this_Atomic_0= ruleAtomic ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )* )
            {

            			newCompositeNode(grammarAccess.getOperationAccess().getAtomicParserRuleCall_0());
            		
            pushFollow(FOLLOW_19);
            this_Atomic_0=ruleAtomic();

            state._fsp--;


            			current = this_Atomic_0;
            			afterParserOrEnumRuleCall();
            		
            // InternalMyDsl.g:1142:3: ( () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )* )
            // InternalMyDsl.g:1143:4: () ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )*
            {
            // InternalMyDsl.g:1143:4: ()
            // InternalMyDsl.g:1144:5: 
            {

            					current = forceCreateModelElementAndSet(
            						grammarAccess.getOperationAccess().getOperationLeftAction_1_0(),
            						current);
            				

            }

            // InternalMyDsl.g:1150:4: ( ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) ) )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( ((LA21_0>=13 && LA21_0<=25)) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // InternalMyDsl.g:1151:5: ( (lv_operator_2_0= ruleOperator ) ) ( (lv_right_3_0= ruleAtomicOrFunctionCall ) )
            	    {
            	    // InternalMyDsl.g:1151:5: ( (lv_operator_2_0= ruleOperator ) )
            	    // InternalMyDsl.g:1152:6: (lv_operator_2_0= ruleOperator )
            	    {
            	    // InternalMyDsl.g:1152:6: (lv_operator_2_0= ruleOperator )
            	    // InternalMyDsl.g:1153:7: lv_operator_2_0= ruleOperator
            	    {

            	    							newCompositeNode(grammarAccess.getOperationAccess().getOperatorOperatorParserRuleCall_1_1_0_0());
            	    						
            	    pushFollow(FOLLOW_20);
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

            	    // InternalMyDsl.g:1170:5: ( (lv_right_3_0= ruleAtomicOrFunctionCall ) )
            	    // InternalMyDsl.g:1171:6: (lv_right_3_0= ruleAtomicOrFunctionCall )
            	    {
            	    // InternalMyDsl.g:1171:6: (lv_right_3_0= ruleAtomicOrFunctionCall )
            	    // InternalMyDsl.g:1172:7: lv_right_3_0= ruleAtomicOrFunctionCall
            	    {

            	    							newCompositeNode(grammarAccess.getOperationAccess().getRightAtomicOrFunctionCallParserRuleCall_1_1_1_0());
            	    						
            	    pushFollow(FOLLOW_19);
            	    lv_right_3_0=ruleAtomicOrFunctionCall();

            	    state._fsp--;


            	    							if (current==null) {
            	    								current = createModelElementForParent(grammarAccess.getOperationRule());
            	    							}
            	    							add(
            	    								current,
            	    								"right",
            	    								lv_right_3_0,
            	    								"org.xtext.example.mydsl.MyDsl.AtomicOrFunctionCall");
            	    							afterParserOrEnumRuleCall();
            	    						

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop21;
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
    // InternalMyDsl.g:1195:1: entryRuleArrayDimension returns [EObject current=null] : iv_ruleArrayDimension= ruleArrayDimension EOF ;
    public final EObject entryRuleArrayDimension() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleArrayDimension = null;


        try {
            // InternalMyDsl.g:1195:55: (iv_ruleArrayDimension= ruleArrayDimension EOF )
            // InternalMyDsl.g:1196:2: iv_ruleArrayDimension= ruleArrayDimension EOF
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
    // InternalMyDsl.g:1202:1: ruleArrayDimension returns [EObject current=null] : ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' ) ;
    public final EObject ruleArrayDimension() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token lv_size_3_0=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalMyDsl.g:1208:2: ( ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' ) )
            // InternalMyDsl.g:1209:2: ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' )
            {
            // InternalMyDsl.g:1209:2: ( () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']' )
            // InternalMyDsl.g:1210:3: () otherlv_1= '[' ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) ) otherlv_4= ']'
            {
            // InternalMyDsl.g:1210:3: ()
            // InternalMyDsl.g:1211:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getArrayDimensionAccess().getArrayDimensionAction_0(),
            					current);
            			

            }

            otherlv_1=(Token)match(input,45,FOLLOW_21); 

            			newLeafNode(otherlv_1, grammarAccess.getArrayDimensionAccess().getLeftSquareBracketKeyword_1());
            		
            // InternalMyDsl.g:1221:3: ( ( (otherlv_2= RULE_ID ) ) | ( (lv_size_3_0= RULE_INT ) ) )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==RULE_ID) ) {
                alt22=1;
            }
            else if ( (LA22_0==RULE_INT) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;
            }
            switch (alt22) {
                case 1 :
                    // InternalMyDsl.g:1222:4: ( (otherlv_2= RULE_ID ) )
                    {
                    // InternalMyDsl.g:1222:4: ( (otherlv_2= RULE_ID ) )
                    // InternalMyDsl.g:1223:5: (otherlv_2= RULE_ID )
                    {
                    // InternalMyDsl.g:1223:5: (otherlv_2= RULE_ID )
                    // InternalMyDsl.g:1224:6: otherlv_2= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getArrayDimensionRule());
                    						}
                    					
                    otherlv_2=(Token)match(input,RULE_ID,FOLLOW_22); 

                    						newLeafNode(otherlv_2, grammarAccess.getArrayDimensionAccess().getIndexVariableSymbolCrossReference_2_0_0());
                    					

                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1236:4: ( (lv_size_3_0= RULE_INT ) )
                    {
                    // InternalMyDsl.g:1236:4: ( (lv_size_3_0= RULE_INT ) )
                    // InternalMyDsl.g:1237:5: (lv_size_3_0= RULE_INT )
                    {
                    // InternalMyDsl.g:1237:5: (lv_size_3_0= RULE_INT )
                    // InternalMyDsl.g:1238:6: lv_size_3_0= RULE_INT
                    {
                    lv_size_3_0=(Token)match(input,RULE_INT,FOLLOW_22); 

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

            otherlv_4=(Token)match(input,46,FOLLOW_2); 

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


    // $ANTLR start "entryRuleVariableDecleration"
    // InternalMyDsl.g:1263:1: entryRuleVariableDecleration returns [EObject current=null] : iv_ruleVariableDecleration= ruleVariableDecleration EOF ;
    public final EObject entryRuleVariableDecleration() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVariableDecleration = null;


        try {
            // InternalMyDsl.g:1263:60: (iv_ruleVariableDecleration= ruleVariableDecleration EOF )
            // InternalMyDsl.g:1264:2: iv_ruleVariableDecleration= ruleVariableDecleration EOF
            {
             newCompositeNode(grammarAccess.getVariableDeclerationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleVariableDecleration=ruleVariableDecleration();

            state._fsp--;

             current =iv_ruleVariableDecleration; 
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
    // $ANTLR end "entryRuleVariableDecleration"


    // $ANTLR start "ruleVariableDecleration"
    // InternalMyDsl.g:1270:1: ruleVariableDecleration returns [EObject current=null] : ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) ) ;
    public final EObject ruleVariableDecleration() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        Token lv_name_2_0=null;
        EObject lv_type_0_0 = null;

        EObject lv_dimension_3_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1276:2: ( ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) ) )
            // InternalMyDsl.g:1277:2: ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) )
            {
            // InternalMyDsl.g:1277:2: ( ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) ) )
            // InternalMyDsl.g:1278:3: ( (lv_type_0_0= ruleVariableType ) ) ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) )
            {
            // InternalMyDsl.g:1278:3: ( (lv_type_0_0= ruleVariableType ) )
            // InternalMyDsl.g:1279:4: (lv_type_0_0= ruleVariableType )
            {
            // InternalMyDsl.g:1279:4: (lv_type_0_0= ruleVariableType )
            // InternalMyDsl.g:1280:5: lv_type_0_0= ruleVariableType
            {

            					newCompositeNode(grammarAccess.getVariableDeclerationAccess().getTypeVariableTypeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_7);
            lv_type_0_0=ruleVariableType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getVariableDeclerationRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"org.xtext.example.mydsl.MyDsl.VariableType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:1297:3: ( ( (lv_name_1_0= RULE_ID ) ) | ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ ) )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_ID) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==45) ) {
                    alt24=2;
                }
                else if ( (LA24_1==EOF||(LA24_1>=RULE_ID && LA24_1<=RULE_BOOLEAN)||LA24_1==26||(LA24_1>=28 && LA24_1<=33)||(LA24_1>=35 && LA24_1<=36)||LA24_1==38||LA24_1==42||LA24_1==44) ) {
                    alt24=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 24, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // InternalMyDsl.g:1298:4: ( (lv_name_1_0= RULE_ID ) )
                    {
                    // InternalMyDsl.g:1298:4: ( (lv_name_1_0= RULE_ID ) )
                    // InternalMyDsl.g:1299:5: (lv_name_1_0= RULE_ID )
                    {
                    // InternalMyDsl.g:1299:5: (lv_name_1_0= RULE_ID )
                    // InternalMyDsl.g:1300:6: lv_name_1_0= RULE_ID
                    {
                    lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

                    						newLeafNode(lv_name_1_0, grammarAccess.getVariableDeclerationAccess().getNameIDTerminalRuleCall_1_0_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getVariableDeclerationRule());
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
                    // InternalMyDsl.g:1317:4: ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ )
                    {
                    // InternalMyDsl.g:1317:4: ( ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+ )
                    // InternalMyDsl.g:1318:5: ( (lv_name_2_0= RULE_ID ) ) ( (lv_dimension_3_0= ruleArrayDimension ) )+
                    {
                    // InternalMyDsl.g:1318:5: ( (lv_name_2_0= RULE_ID ) )
                    // InternalMyDsl.g:1319:6: (lv_name_2_0= RULE_ID )
                    {
                    // InternalMyDsl.g:1319:6: (lv_name_2_0= RULE_ID )
                    // InternalMyDsl.g:1320:7: lv_name_2_0= RULE_ID
                    {
                    lv_name_2_0=(Token)match(input,RULE_ID,FOLLOW_23); 

                    							newLeafNode(lv_name_2_0, grammarAccess.getVariableDeclerationAccess().getNameIDTerminalRuleCall_1_1_0_0());
                    						

                    							if (current==null) {
                    								current = createModelElement(grammarAccess.getVariableDeclerationRule());
                    							}
                    							setWithLastConsumed(
                    								current,
                    								"name",
                    								lv_name_2_0,
                    								"org.eclipse.xtext.common.Terminals.ID");
                    						

                    }


                    }

                    // InternalMyDsl.g:1336:5: ( (lv_dimension_3_0= ruleArrayDimension ) )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==45) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // InternalMyDsl.g:1337:6: (lv_dimension_3_0= ruleArrayDimension )
                    	    {
                    	    // InternalMyDsl.g:1337:6: (lv_dimension_3_0= ruleArrayDimension )
                    	    // InternalMyDsl.g:1338:7: lv_dimension_3_0= ruleArrayDimension
                    	    {

                    	    							newCompositeNode(grammarAccess.getVariableDeclerationAccess().getDimensionArrayDimensionParserRuleCall_1_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_24);
                    	    lv_dimension_3_0=ruleArrayDimension();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getVariableDeclerationRule());
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
                    	    if ( cnt23 >= 1 ) break loop23;
                                EarlyExitException eee =
                                    new EarlyExitException(23, input);
                                throw eee;
                        }
                        cnt23++;
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
    // $ANTLR end "ruleVariableDecleration"


    // $ANTLR start "entryRuleFunctionParameter"
    // InternalMyDsl.g:1361:1: entryRuleFunctionParameter returns [EObject current=null] : iv_ruleFunctionParameter= ruleFunctionParameter EOF ;
    public final EObject entryRuleFunctionParameter() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionParameter = null;


        try {
            // InternalMyDsl.g:1361:58: (iv_ruleFunctionParameter= ruleFunctionParameter EOF )
            // InternalMyDsl.g:1362:2: iv_ruleFunctionParameter= ruleFunctionParameter EOF
            {
             newCompositeNode(grammarAccess.getFunctionParameterRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionParameter=ruleFunctionParameter();

            state._fsp--;

             current =iv_ruleFunctionParameter; 
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
    // $ANTLR end "entryRuleFunctionParameter"


    // $ANTLR start "ruleFunctionParameter"
    // InternalMyDsl.g:1368:1: ruleFunctionParameter returns [EObject current=null] : ( ( (lv_type_0_0= ruleVariableType ) ) ( (lv_name_1_0= RULE_ID ) ) ) ;
    public final EObject ruleFunctionParameter() throws RecognitionException {
        EObject current = null;

        Token lv_name_1_0=null;
        EObject lv_type_0_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1374:2: ( ( ( (lv_type_0_0= ruleVariableType ) ) ( (lv_name_1_0= RULE_ID ) ) ) )
            // InternalMyDsl.g:1375:2: ( ( (lv_type_0_0= ruleVariableType ) ) ( (lv_name_1_0= RULE_ID ) ) )
            {
            // InternalMyDsl.g:1375:2: ( ( (lv_type_0_0= ruleVariableType ) ) ( (lv_name_1_0= RULE_ID ) ) )
            // InternalMyDsl.g:1376:3: ( (lv_type_0_0= ruleVariableType ) ) ( (lv_name_1_0= RULE_ID ) )
            {
            // InternalMyDsl.g:1376:3: ( (lv_type_0_0= ruleVariableType ) )
            // InternalMyDsl.g:1377:4: (lv_type_0_0= ruleVariableType )
            {
            // InternalMyDsl.g:1377:4: (lv_type_0_0= ruleVariableType )
            // InternalMyDsl.g:1378:5: lv_type_0_0= ruleVariableType
            {

            					newCompositeNode(grammarAccess.getFunctionParameterAccess().getTypeVariableTypeParserRuleCall_0_0());
            				
            pushFollow(FOLLOW_7);
            lv_type_0_0=ruleVariableType();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getFunctionParameterRule());
            					}
            					set(
            						current,
            						"type",
            						lv_type_0_0,
            						"org.xtext.example.mydsl.MyDsl.VariableType");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            // InternalMyDsl.g:1395:3: ( (lv_name_1_0= RULE_ID ) )
            // InternalMyDsl.g:1396:4: (lv_name_1_0= RULE_ID )
            {
            // InternalMyDsl.g:1396:4: (lv_name_1_0= RULE_ID )
            // InternalMyDsl.g:1397:5: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFunctionParameterAccess().getNameIDTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFunctionParameterRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.eclipse.xtext.common.Terminals.ID");
            				

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
    // $ANTLR end "ruleFunctionParameter"


    // $ANTLR start "entryRuleAtomic"
    // InternalMyDsl.g:1417:1: entryRuleAtomic returns [EObject current=null] : iv_ruleAtomic= ruleAtomic EOF ;
    public final EObject entryRuleAtomic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomic = null;


        try {
            // InternalMyDsl.g:1417:47: (iv_ruleAtomic= ruleAtomic EOF )
            // InternalMyDsl.g:1418:2: iv_ruleAtomic= ruleAtomic EOF
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
    // InternalMyDsl.g:1424:1: ruleAtomic returns [EObject current=null] : ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) ) ;
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
            // InternalMyDsl.g:1430:2: ( ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) ) )
            // InternalMyDsl.g:1431:2: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) )
            {
            // InternalMyDsl.g:1431:2: ( ( () ( (lv_value_1_0= RULE_INT ) ) ) | ( () ( (lv_value_3_0= RULE_DOUBLE ) ) ) | ( () ( (lv_value_5_0= RULE_STRING ) ) ) | ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) ) | ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ ) | ( () ( (otherlv_12= RULE_ID ) ) ) )
            int alt26=6;
            switch ( input.LA(1) ) {
            case RULE_INT:
                {
                alt26=1;
                }
                break;
            case RULE_DOUBLE:
                {
                alt26=2;
                }
                break;
            case RULE_STRING:
                {
                alt26=3;
                }
                break;
            case RULE_BOOLEAN:
                {
                alt26=4;
                }
                break;
            case RULE_ID:
                {
                int LA26_5 = input.LA(2);

                if ( (LA26_5==EOF||(LA26_5>=RULE_ID && LA26_5<=RULE_BOOLEAN)||(LA26_5>=13 && LA26_5<=27)||(LA26_5>=35 && LA26_5<=38)||LA26_5==40||(LA26_5>=42 && LA26_5<=44)||LA26_5==47) ) {
                    alt26=6;
                }
                else if ( (LA26_5==45) ) {
                    alt26=5;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 26, 5, input);

                    throw nvae;
                }
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;
            }

            switch (alt26) {
                case 1 :
                    // InternalMyDsl.g:1432:3: ( () ( (lv_value_1_0= RULE_INT ) ) )
                    {
                    // InternalMyDsl.g:1432:3: ( () ( (lv_value_1_0= RULE_INT ) ) )
                    // InternalMyDsl.g:1433:4: () ( (lv_value_1_0= RULE_INT ) )
                    {
                    // InternalMyDsl.g:1433:4: ()
                    // InternalMyDsl.g:1434:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getIntegerReferenceAction_0_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1440:4: ( (lv_value_1_0= RULE_INT ) )
                    // InternalMyDsl.g:1441:5: (lv_value_1_0= RULE_INT )
                    {
                    // InternalMyDsl.g:1441:5: (lv_value_1_0= RULE_INT )
                    // InternalMyDsl.g:1442:6: lv_value_1_0= RULE_INT
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
                    // InternalMyDsl.g:1460:3: ( () ( (lv_value_3_0= RULE_DOUBLE ) ) )
                    {
                    // InternalMyDsl.g:1460:3: ( () ( (lv_value_3_0= RULE_DOUBLE ) ) )
                    // InternalMyDsl.g:1461:4: () ( (lv_value_3_0= RULE_DOUBLE ) )
                    {
                    // InternalMyDsl.g:1461:4: ()
                    // InternalMyDsl.g:1462:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getDoubleReferenceAction_1_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1468:4: ( (lv_value_3_0= RULE_DOUBLE ) )
                    // InternalMyDsl.g:1469:5: (lv_value_3_0= RULE_DOUBLE )
                    {
                    // InternalMyDsl.g:1469:5: (lv_value_3_0= RULE_DOUBLE )
                    // InternalMyDsl.g:1470:6: lv_value_3_0= RULE_DOUBLE
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
                    // InternalMyDsl.g:1488:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    {
                    // InternalMyDsl.g:1488:3: ( () ( (lv_value_5_0= RULE_STRING ) ) )
                    // InternalMyDsl.g:1489:4: () ( (lv_value_5_0= RULE_STRING ) )
                    {
                    // InternalMyDsl.g:1489:4: ()
                    // InternalMyDsl.g:1490:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getStringReferenceAction_2_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1496:4: ( (lv_value_5_0= RULE_STRING ) )
                    // InternalMyDsl.g:1497:5: (lv_value_5_0= RULE_STRING )
                    {
                    // InternalMyDsl.g:1497:5: (lv_value_5_0= RULE_STRING )
                    // InternalMyDsl.g:1498:6: lv_value_5_0= RULE_STRING
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
                    // InternalMyDsl.g:1516:3: ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) )
                    {
                    // InternalMyDsl.g:1516:3: ( () ( (lv_value_7_0= RULE_BOOLEAN ) ) )
                    // InternalMyDsl.g:1517:4: () ( (lv_value_7_0= RULE_BOOLEAN ) )
                    {
                    // InternalMyDsl.g:1517:4: ()
                    // InternalMyDsl.g:1518:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getBooleanReferenceAction_3_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1524:4: ( (lv_value_7_0= RULE_BOOLEAN ) )
                    // InternalMyDsl.g:1525:5: (lv_value_7_0= RULE_BOOLEAN )
                    {
                    // InternalMyDsl.g:1525:5: (lv_value_7_0= RULE_BOOLEAN )
                    // InternalMyDsl.g:1526:6: lv_value_7_0= RULE_BOOLEAN
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
                    // InternalMyDsl.g:1544:3: ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ )
                    {
                    // InternalMyDsl.g:1544:3: ( () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+ )
                    // InternalMyDsl.g:1545:4: () ( (otherlv_9= RULE_ID ) ) ( (lv_dimension_10_0= ruleArrayDimension ) )+
                    {
                    // InternalMyDsl.g:1545:4: ()
                    // InternalMyDsl.g:1546:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getArrayReferenceAction_4_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1552:4: ( (otherlv_9= RULE_ID ) )
                    // InternalMyDsl.g:1553:5: (otherlv_9= RULE_ID )
                    {
                    // InternalMyDsl.g:1553:5: (otherlv_9= RULE_ID )
                    // InternalMyDsl.g:1554:6: otherlv_9= RULE_ID
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getAtomicRule());
                    						}
                    					
                    otherlv_9=(Token)match(input,RULE_ID,FOLLOW_23); 

                    						newLeafNode(otherlv_9, grammarAccess.getAtomicAccess().getVariableReferenceVariableSymbolCrossReference_4_1_0());
                    					

                    }


                    }

                    // InternalMyDsl.g:1565:4: ( (lv_dimension_10_0= ruleArrayDimension ) )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==45) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // InternalMyDsl.g:1566:5: (lv_dimension_10_0= ruleArrayDimension )
                    	    {
                    	    // InternalMyDsl.g:1566:5: (lv_dimension_10_0= ruleArrayDimension )
                    	    // InternalMyDsl.g:1567:6: lv_dimension_10_0= ruleArrayDimension
                    	    {

                    	    						newCompositeNode(grammarAccess.getAtomicAccess().getDimensionArrayDimensionParserRuleCall_4_2_0());
                    	    					
                    	    pushFollow(FOLLOW_24);
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
                    	    if ( cnt25 >= 1 ) break loop25;
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);


                    }


                    }
                    break;
                case 6 :
                    // InternalMyDsl.g:1586:3: ( () ( (otherlv_12= RULE_ID ) ) )
                    {
                    // InternalMyDsl.g:1586:3: ( () ( (otherlv_12= RULE_ID ) ) )
                    // InternalMyDsl.g:1587:4: () ( (otherlv_12= RULE_ID ) )
                    {
                    // InternalMyDsl.g:1587:4: ()
                    // InternalMyDsl.g:1588:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getAtomicAccess().getVariableReferenceAction_5_0(),
                    						current);
                    				

                    }

                    // InternalMyDsl.g:1594:4: ( (otherlv_12= RULE_ID ) )
                    // InternalMyDsl.g:1595:5: (otherlv_12= RULE_ID )
                    {
                    // InternalMyDsl.g:1595:5: (otherlv_12= RULE_ID )
                    // InternalMyDsl.g:1596:6: otherlv_12= RULE_ID
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


    // $ANTLR start "entryRuleAtomicOrFunctionCall"
    // InternalMyDsl.g:1612:1: entryRuleAtomicOrFunctionCall returns [EObject current=null] : iv_ruleAtomicOrFunctionCall= ruleAtomicOrFunctionCall EOF ;
    public final EObject entryRuleAtomicOrFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAtomicOrFunctionCall = null;


        try {
            // InternalMyDsl.g:1612:61: (iv_ruleAtomicOrFunctionCall= ruleAtomicOrFunctionCall EOF )
            // InternalMyDsl.g:1613:2: iv_ruleAtomicOrFunctionCall= ruleAtomicOrFunctionCall EOF
            {
             newCompositeNode(grammarAccess.getAtomicOrFunctionCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAtomicOrFunctionCall=ruleAtomicOrFunctionCall();

            state._fsp--;

             current =iv_ruleAtomicOrFunctionCall; 
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
    // $ANTLR end "entryRuleAtomicOrFunctionCall"


    // $ANTLR start "ruleAtomicOrFunctionCall"
    // InternalMyDsl.g:1619:1: ruleAtomicOrFunctionCall returns [EObject current=null] : (this_Atomic_0= ruleAtomic | this_AbstractFunctionCall_1= ruleAbstractFunctionCall ) ;
    public final EObject ruleAtomicOrFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject this_Atomic_0 = null;

        EObject this_AbstractFunctionCall_1 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1625:2: ( (this_Atomic_0= ruleAtomic | this_AbstractFunctionCall_1= ruleAbstractFunctionCall ) )
            // InternalMyDsl.g:1626:2: (this_Atomic_0= ruleAtomic | this_AbstractFunctionCall_1= ruleAbstractFunctionCall )
            {
            // InternalMyDsl.g:1626:2: (this_Atomic_0= ruleAtomic | this_AbstractFunctionCall_1= ruleAbstractFunctionCall )
            int alt27=2;
            switch ( input.LA(1) ) {
            case RULE_INT:
            case RULE_DOUBLE:
            case RULE_STRING:
            case RULE_BOOLEAN:
                {
                alt27=1;
                }
                break;
            case RULE_ID:
                {
                int LA27_2 = input.LA(2);

                if ( (LA27_2==EOF||(LA27_2>=RULE_ID && LA27_2<=RULE_BOOLEAN)||(LA27_2>=13 && LA27_2<=27)||(LA27_2>=35 && LA27_2<=38)||LA27_2==40||(LA27_2>=42 && LA27_2<=45)) ) {
                    alt27=1;
                }
                else if ( (LA27_2==39) ) {
                    alt27=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 27, 2, input);

                    throw nvae;
                }
                }
                break;
            case 26:
                {
                alt27=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 27, 0, input);

                throw nvae;
            }

            switch (alt27) {
                case 1 :
                    // InternalMyDsl.g:1627:3: this_Atomic_0= ruleAtomic
                    {

                    			newCompositeNode(grammarAccess.getAtomicOrFunctionCallAccess().getAtomicParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Atomic_0=ruleAtomic();

                    state._fsp--;


                    			current = this_Atomic_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1636:3: this_AbstractFunctionCall_1= ruleAbstractFunctionCall
                    {

                    			newCompositeNode(grammarAccess.getAtomicOrFunctionCallAccess().getAbstractFunctionCallParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_AbstractFunctionCall_1=ruleAbstractFunctionCall();

                    state._fsp--;


                    			current = this_AbstractFunctionCall_1;
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
    // $ANTLR end "ruleAtomicOrFunctionCall"


    // $ANTLR start "entryRuleFunctionCall"
    // InternalMyDsl.g:1648:1: entryRuleFunctionCall returns [EObject current=null] : iv_ruleFunctionCall= ruleFunctionCall EOF ;
    public final EObject entryRuleFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFunctionCall = null;


        try {
            // InternalMyDsl.g:1648:53: (iv_ruleFunctionCall= ruleFunctionCall EOF )
            // InternalMyDsl.g:1649:2: iv_ruleFunctionCall= ruleFunctionCall EOF
            {
             newCompositeNode(grammarAccess.getFunctionCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFunctionCall=ruleFunctionCall();

            state._fsp--;

             current =iv_ruleFunctionCall; 
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
    // $ANTLR end "entryRuleFunctionCall"


    // $ANTLR start "ruleFunctionCall"
    // InternalMyDsl.g:1655:1: ruleFunctionCall returns [EObject current=null] : ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' ) ;
    public final EObject ruleFunctionCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        EObject lv_parameters_3_0 = null;

        EObject lv_parameters_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1661:2: ( ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' ) )
            // InternalMyDsl.g:1662:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' )
            {
            // InternalMyDsl.g:1662:2: ( () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' )
            // InternalMyDsl.g:1663:3: () ( (otherlv_1= RULE_ID ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')'
            {
            // InternalMyDsl.g:1663:3: ()
            // InternalMyDsl.g:1664:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getFunctionCallAccess().getFunctionCallAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:1670:3: ( (otherlv_1= RULE_ID ) )
            // InternalMyDsl.g:1671:4: (otherlv_1= RULE_ID )
            {
            // InternalMyDsl.g:1671:4: (otherlv_1= RULE_ID )
            // InternalMyDsl.g:1672:5: otherlv_1= RULE_ID
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFunctionCallRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ID,FOLLOW_12); 

            					newLeafNode(otherlv_1, grammarAccess.getFunctionCallAccess().getFunctionFunctionDefinitionCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,39,FOLLOW_25); 

            			newLeafNode(otherlv_2, grammarAccess.getFunctionCallAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:1687:3: ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( ((LA29_0>=RULE_ID && LA29_0<=RULE_BOOLEAN)) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // InternalMyDsl.g:1688:4: ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )*
                    {
                    // InternalMyDsl.g:1688:4: ( (lv_parameters_3_0= ruleAtomic ) )
                    // InternalMyDsl.g:1689:5: (lv_parameters_3_0= ruleAtomic )
                    {
                    // InternalMyDsl.g:1689:5: (lv_parameters_3_0= ruleAtomic )
                    // InternalMyDsl.g:1690:6: lv_parameters_3_0= ruleAtomic
                    {

                    						newCompositeNode(grammarAccess.getFunctionCallAccess().getParametersAtomicParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_26);
                    lv_parameters_3_0=ruleAtomic();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getFunctionCallRule());
                    						}
                    						add(
                    							current,
                    							"parameters",
                    							lv_parameters_3_0,
                    							"org.xtext.example.mydsl.MyDsl.Atomic");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMyDsl.g:1707:4: (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )*
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==47) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // InternalMyDsl.g:1708:5: otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) )
                    	    {
                    	    otherlv_4=(Token)match(input,47,FOLLOW_13); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getFunctionCallAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalMyDsl.g:1712:5: ( (lv_parameters_5_0= ruleAtomic ) )
                    	    // InternalMyDsl.g:1713:6: (lv_parameters_5_0= ruleAtomic )
                    	    {
                    	    // InternalMyDsl.g:1713:6: (lv_parameters_5_0= ruleAtomic )
                    	    // InternalMyDsl.g:1714:7: lv_parameters_5_0= ruleAtomic
                    	    {

                    	    							newCompositeNode(grammarAccess.getFunctionCallAccess().getParametersAtomicParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_26);
                    	    lv_parameters_5_0=ruleAtomic();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getFunctionCallRule());
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
                    	    break loop28;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,40,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getFunctionCallAccess().getRightParenthesisKeyword_4());
            		

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
    // $ANTLR end "ruleFunctionCall"


    // $ANTLR start "entryRuleBuiltinFunctionCall"
    // InternalMyDsl.g:1741:1: entryRuleBuiltinFunctionCall returns [EObject current=null] : iv_ruleBuiltinFunctionCall= ruleBuiltinFunctionCall EOF ;
    public final EObject entryRuleBuiltinFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleBuiltinFunctionCall = null;


        try {
            // InternalMyDsl.g:1741:60: (iv_ruleBuiltinFunctionCall= ruleBuiltinFunctionCall EOF )
            // InternalMyDsl.g:1742:2: iv_ruleBuiltinFunctionCall= ruleBuiltinFunctionCall EOF
            {
             newCompositeNode(grammarAccess.getBuiltinFunctionCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleBuiltinFunctionCall=ruleBuiltinFunctionCall();

            state._fsp--;

             current =iv_ruleBuiltinFunctionCall; 
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
    // $ANTLR end "entryRuleBuiltinFunctionCall"


    // $ANTLR start "ruleBuiltinFunctionCall"
    // InternalMyDsl.g:1748:1: ruleBuiltinFunctionCall returns [EObject current=null] : ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' ) ;
    public final EObject ruleBuiltinFunctionCall() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token otherlv_6=null;
        AntlrDatatypeRuleToken lv_function_1_0 = null;

        EObject lv_parameters_3_0 = null;

        EObject lv_parameters_5_0 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1754:2: ( ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' ) )
            // InternalMyDsl.g:1755:2: ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' )
            {
            // InternalMyDsl.g:1755:2: ( () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')' )
            // InternalMyDsl.g:1756:3: () ( (lv_function_1_0= ruleBuiltinFunctions ) ) otherlv_2= '(' ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )? otherlv_6= ')'
            {
            // InternalMyDsl.g:1756:3: ()
            // InternalMyDsl.g:1757:4: 
            {

            				current = forceCreateModelElement(
            					grammarAccess.getBuiltinFunctionCallAccess().getBuiltinFunctionCallAction_0(),
            					current);
            			

            }

            // InternalMyDsl.g:1763:3: ( (lv_function_1_0= ruleBuiltinFunctions ) )
            // InternalMyDsl.g:1764:4: (lv_function_1_0= ruleBuiltinFunctions )
            {
            // InternalMyDsl.g:1764:4: (lv_function_1_0= ruleBuiltinFunctions )
            // InternalMyDsl.g:1765:5: lv_function_1_0= ruleBuiltinFunctions
            {

            					newCompositeNode(grammarAccess.getBuiltinFunctionCallAccess().getFunctionBuiltinFunctionsParserRuleCall_1_0());
            				
            pushFollow(FOLLOW_12);
            lv_function_1_0=ruleBuiltinFunctions();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getBuiltinFunctionCallRule());
            					}
            					set(
            						current,
            						"function",
            						lv_function_1_0,
            						"org.xtext.example.mydsl.MyDsl.BuiltinFunctions");
            					afterParserOrEnumRuleCall();
            				

            }


            }

            otherlv_2=(Token)match(input,39,FOLLOW_25); 

            			newLeafNode(otherlv_2, grammarAccess.getBuiltinFunctionCallAccess().getLeftParenthesisKeyword_2());
            		
            // InternalMyDsl.g:1786:3: ( ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )* )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( ((LA31_0>=RULE_ID && LA31_0<=RULE_BOOLEAN)) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // InternalMyDsl.g:1787:4: ( (lv_parameters_3_0= ruleAtomic ) ) (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )*
                    {
                    // InternalMyDsl.g:1787:4: ( (lv_parameters_3_0= ruleAtomic ) )
                    // InternalMyDsl.g:1788:5: (lv_parameters_3_0= ruleAtomic )
                    {
                    // InternalMyDsl.g:1788:5: (lv_parameters_3_0= ruleAtomic )
                    // InternalMyDsl.g:1789:6: lv_parameters_3_0= ruleAtomic
                    {

                    						newCompositeNode(grammarAccess.getBuiltinFunctionCallAccess().getParametersAtomicParserRuleCall_3_0_0());
                    					
                    pushFollow(FOLLOW_26);
                    lv_parameters_3_0=ruleAtomic();

                    state._fsp--;


                    						if (current==null) {
                    							current = createModelElementForParent(grammarAccess.getBuiltinFunctionCallRule());
                    						}
                    						add(
                    							current,
                    							"parameters",
                    							lv_parameters_3_0,
                    							"org.xtext.example.mydsl.MyDsl.Atomic");
                    						afterParserOrEnumRuleCall();
                    					

                    }


                    }

                    // InternalMyDsl.g:1806:4: (otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) ) )*
                    loop30:
                    do {
                        int alt30=2;
                        int LA30_0 = input.LA(1);

                        if ( (LA30_0==47) ) {
                            alt30=1;
                        }


                        switch (alt30) {
                    	case 1 :
                    	    // InternalMyDsl.g:1807:5: otherlv_4= ',' ( (lv_parameters_5_0= ruleAtomic ) )
                    	    {
                    	    otherlv_4=(Token)match(input,47,FOLLOW_13); 

                    	    					newLeafNode(otherlv_4, grammarAccess.getBuiltinFunctionCallAccess().getCommaKeyword_3_1_0());
                    	    				
                    	    // InternalMyDsl.g:1811:5: ( (lv_parameters_5_0= ruleAtomic ) )
                    	    // InternalMyDsl.g:1812:6: (lv_parameters_5_0= ruleAtomic )
                    	    {
                    	    // InternalMyDsl.g:1812:6: (lv_parameters_5_0= ruleAtomic )
                    	    // InternalMyDsl.g:1813:7: lv_parameters_5_0= ruleAtomic
                    	    {

                    	    							newCompositeNode(grammarAccess.getBuiltinFunctionCallAccess().getParametersAtomicParserRuleCall_3_1_1_0());
                    	    						
                    	    pushFollow(FOLLOW_26);
                    	    lv_parameters_5_0=ruleAtomic();

                    	    state._fsp--;


                    	    							if (current==null) {
                    	    								current = createModelElementForParent(grammarAccess.getBuiltinFunctionCallRule());
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
                    	    break loop30;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_6=(Token)match(input,40,FOLLOW_2); 

            			newLeafNode(otherlv_6, grammarAccess.getBuiltinFunctionCallAccess().getRightParenthesisKeyword_4());
            		

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
    // $ANTLR end "ruleBuiltinFunctionCall"


    // $ANTLR start "entryRuleAbstractFunctionCall"
    // InternalMyDsl.g:1840:1: entryRuleAbstractFunctionCall returns [EObject current=null] : iv_ruleAbstractFunctionCall= ruleAbstractFunctionCall EOF ;
    public final EObject entryRuleAbstractFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAbstractFunctionCall = null;


        try {
            // InternalMyDsl.g:1840:61: (iv_ruleAbstractFunctionCall= ruleAbstractFunctionCall EOF )
            // InternalMyDsl.g:1841:2: iv_ruleAbstractFunctionCall= ruleAbstractFunctionCall EOF
            {
             newCompositeNode(grammarAccess.getAbstractFunctionCallRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAbstractFunctionCall=ruleAbstractFunctionCall();

            state._fsp--;

             current =iv_ruleAbstractFunctionCall; 
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
    // $ANTLR end "entryRuleAbstractFunctionCall"


    // $ANTLR start "ruleAbstractFunctionCall"
    // InternalMyDsl.g:1847:1: ruleAbstractFunctionCall returns [EObject current=null] : (this_FunctionCall_0= ruleFunctionCall | this_BuiltinFunctionCall_1= ruleBuiltinFunctionCall ) ;
    public final EObject ruleAbstractFunctionCall() throws RecognitionException {
        EObject current = null;

        EObject this_FunctionCall_0 = null;

        EObject this_BuiltinFunctionCall_1 = null;



        	enterRule();

        try {
            // InternalMyDsl.g:1853:2: ( (this_FunctionCall_0= ruleFunctionCall | this_BuiltinFunctionCall_1= ruleBuiltinFunctionCall ) )
            // InternalMyDsl.g:1854:2: (this_FunctionCall_0= ruleFunctionCall | this_BuiltinFunctionCall_1= ruleBuiltinFunctionCall )
            {
            // InternalMyDsl.g:1854:2: (this_FunctionCall_0= ruleFunctionCall | this_BuiltinFunctionCall_1= ruleBuiltinFunctionCall )
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==RULE_ID) ) {
                alt32=1;
            }
            else if ( (LA32_0==26) ) {
                alt32=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;
            }
            switch (alt32) {
                case 1 :
                    // InternalMyDsl.g:1855:3: this_FunctionCall_0= ruleFunctionCall
                    {

                    			newCompositeNode(grammarAccess.getAbstractFunctionCallAccess().getFunctionCallParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_FunctionCall_0=ruleFunctionCall();

                    state._fsp--;


                    			current = this_FunctionCall_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalMyDsl.g:1864:3: this_BuiltinFunctionCall_1= ruleBuiltinFunctionCall
                    {

                    			newCompositeNode(grammarAccess.getAbstractFunctionCallAccess().getBuiltinFunctionCallParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_BuiltinFunctionCall_1=ruleBuiltinFunctionCall();

                    state._fsp--;


                    			current = this_BuiltinFunctionCall_1;
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
    // $ANTLR end "ruleAbstractFunctionCall"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000380000000L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000300000000L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000070000000L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000200000000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00001458740001F0L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x00001458040001F0L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000008000000000L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x00000000000001F0L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000010000000000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x000014600C0001F0L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000003FFE002L});
    public static final BitSet FOLLOW_20 = new BitSet(new long[]{0x00001440040001F0L});
    public static final BitSet FOLLOW_21 = new BitSet(new long[]{0x0000000000000030L});
    public static final BitSet FOLLOW_22 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_23 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_24 = new BitSet(new long[]{0x0000200000000002L});
    public static final BitSet FOLLOW_25 = new BitSet(new long[]{0x00000100000001F0L});
    public static final BitSet FOLLOW_26 = new BitSet(new long[]{0x0000810000000000L});

}