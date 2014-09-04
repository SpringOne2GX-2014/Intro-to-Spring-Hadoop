// use the shell (made available under variable fsh)

if (!fsh.test(hiveDir)) {
   fsh.mkdir(hiveDir)
}
if (fsh.test(inputDir)) {
   fsh.rmr(inputDir)
}
fsh.mkdir(inputDir); 
fsh.copyFromLocal(localFile, inputDir); 
fsh.chmod(700, inputDir)
if (fsh.test(outputDir)) {
   fsh.rmr(outputDir)
}
def files = fsh.ls("${inputDir}")
if (files.size() <= 1) {
	throw new IllegalStateException("${inputDir} is empty, file copy of ${localFile} failed!")
}
new File("${localFile}").delete()
