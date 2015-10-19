# Based on NSIS MUI Basic Example Script written by Joost Verburg (thx!)
  !include "MUI.nsh"
  Name "Blackwood"
  OutFile "blackwood-install.exe"
  RequestExecutionLevel admin
  InstallDir "$PROGRAMFILES\WWWIC\Blackwood"
  InstallDirRegKey HKCU "Software\Blackwood" ""

  SetCompressor /FINAL /SOLID lzma
  
  ShowInstDetails show
;--------------------------------
 
  !define MUI_PRODUCT "Blackwood"

;Pages
  !insertmacro MUI_PAGE_WELCOME
  !insertmacro MUI_PAGE_LICENSE "Client\LICENSE.txt"
  !insertmacro MUI_PAGE_DIRECTORY
  !insertmacro MUI_PAGE_COMPONENTS
  !insertmacro MUI_PAGE_INSTFILES
  
    # These indented statements modify settings for MUI_PAGE_FINISH
    !define MUI_FINISHPAGE_NOAUTOCLOSE
    !define MUI_FINISHPAGE_RUN
    !define MUI_FINISHPAGE_RUN_NOTCHECKED
    !define MUI_FINISHPAGE_RUN_TEXT "Play Blackwood"
    !define MUI_FINISHPAGE_RUN_FUNCTION "LaunchLink"
  !insertmacro MUI_PAGE_FINISH
   UninstPage uninstConfirm
   UninstPage instfiles
   
  Var fileLocation
  Var fileOptions
  Var fileDebugLocation
 


;Languages
!insertmacro MUI_LANGUAGE "English"
 
;--------------------------------
# No components page, so this is anonymous
Section "Blackwood"
  SectionIn RO
  SetOutPath "$INSTDIR\Client"
  StrCpy $fileLocation "$INSTDIR\Client\jre6\bin\javaw.exe"
  StrCpy $fileOptions '-Djava.security.policy=policy -cp ./lib/*;./ edu.nodak.ndsu.cs.lions.blackwood.Main'
  CreateShortCut "$INSTDIR\Client\Blackwood.lnk" $fileLocation $fileOptions "$INSTDIR\Client\blackwood.ico" "0" "SW_SHOWMINIMIZED"

  File /r "Client\*.*"
  WriteRegStr HKCU "Software\Blackwood" "" $INSTDIR
  WriteUninstaller "$INSTDIR\Uninstall.exe"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "DisplayName" "Blackwood"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "UninstallString" "$INSTDIR\Uninstall.exe"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "DisplayIcon" "$INSTDIR\Client\blackwood.ico"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "InstallLocation" "$INSTDIR"
  WriteRegStr HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "Publisher" "World Wide Web Instructional Committee - North Dakota State University"
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "NoModify" 1
  WriteRegDWORD HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood" "NoRepair" 1
SectionEnd

; Optional section (can be disabled by the user)
Section "Start Menu Shortcuts"
  SetShellVarContext all
  StrCpy $fileLocation "$INSTDIR\Client\jre6\bin\javaw.exe"
  StrCpy $fileDebugLocation "$INSTDIR\Client\jre6\bin\java.exe"
  StrCpy $fileOptions '-Djava.security.policy=policy -cp ./lib/*;./ edu.nodak.ndsu.cs.lions.blackwood.Main'
  CreateDirectory "$SMPROGRAMS\WWWIC"
  CreateDirectory "$SMPROGRAMS\WWWIC\Blackwood"
  CreateShortCut "$SMPROGRAMS\WWWIC\Blackwood\Uninstall.lnk" "$INSTDIR\uninstall.exe" "" "$INSTDIR\uninstall.exe" 0
  CreateShortCut "$SMPROGRAMS\WWWIC\Blackwood\Blackwood.lnk" $fileLocation $fileOptions "$INSTDIR\Client\blackwood.ico" "0" "SW_SHOWMINIMIZED"
; No debug mode for this application, so removing this line.
;  CreateShortCut "$SMPROGRAMS\WWWIC\Blackwood\Blackwood.lnk" $fileDebugLocation $fileOptions "$INSTDIR\Client\geo_icon.ico" "0" "SW_SHOWNORMAL"
  SetOutPath $INSTDIR\Client
SectionEnd

Section "Desktop Shortcut"
  SetShellVarContext all
  StrCpy $fileLocation "$INSTDIR\Client\jre6\bin\javaw.exe"
  StrCpy $fileOptions '-Djava.security.policy=policy -cp ./lib/*;./ edu.nodak.ndsu.cs.lions.blackwood.Main'
  CreateShortCut "$DESKTOP\${MUI_PRODUCT}.lnk" $fileLocation $fileOptions "$INSTDIR\Client\blackwood.ico" "0" "SW_SHOWMINIMIZED"
SectionEnd

Section "Quick Launch Shortcut"
  SetShellVarContext all
  StrCpy $fileLocation "$INSTDIR\Client\jre6\bin\javaw.exe"
  StrCpy $fileOptions '-Djava.security.policy=policy -cp ./lib/*;./ edu.nodak.ndsu.cs.lions.blackwood.Main'
  CreateShortCut "$QUICKLAUNCH\${MUI_PRODUCT}.lnk" $fileLocation $fileOptions "$INSTDIR\Client\blackwood.ico" "0" "SW_SHOWMINIMIZED"
SectionEnd


Section "Uninstall"
  Delete "$INSTDIR\Uninstall.exe"
  Delete "$SMPROGRAMS\WWWIC\Blackwood\Blackwood.lnk"
  Delete "$SMPROGRAMS\WWWIC\Blackwood\Uninstall.lnk"
  
  Delete "$QUICKLAUNCH\Blackwood"
  Delete "$DESKTOP\${MUI_PRODUCT}.lnk"
  RMDir /r "$INSTDIR"
  DeleteRegKey /ifempty HKCU "Software\Blackwood"
  DeleteRegKey HKLM "Software\Microsoft\Windows\CurrentVersion\Uninstall\Blackwood"

SectionEnd
 
Function LaunchLink
  ExecShell "" "$INSTDIR\Client\Blackwood.lnk"
FunctionEnd
