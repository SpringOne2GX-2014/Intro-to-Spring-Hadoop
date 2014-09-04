// use the shell (made available under variable fsh)

println "Results:"
println "========"

new File('/tmp/results.txt').delete()
if (fsh.test(outputDir)) {
   fsh.get("${outputDir}/part-r-00000", '/tmp/results.txt')
}

String results = new File('/tmp/results.txt').text
println results
