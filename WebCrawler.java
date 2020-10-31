/**
 * // This is the HtmlParser's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface HtmlParser {
 *     public List<String> getUrls(String url) {}
 * }
 */

class WebCrawler {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        
        Set<String> result = new HashSet<>();
        
        //We will fetch the hostname from starturl and then check whether all the urls that are connected to startUrl have same hostname or not using BFS. If they have same hostname add them to result list and again perform BFS
        String hostName = getHostName(startUrl);
        
        //Store all the connected nodes for BFS
        Queue<String> queue = new ArrayDeque<>();
        
        queue.add(startUrl);
        
        //It will always be in the result
        result.add(startUrl);
        
        //Performing BFS
        while(!queue.isEmpty()){
            
            String currentUrl = queue.poll();
            
            //We will get all the nodes/urls that are connected to currentUrl
            for(String url: htmlParser.getUrls(currentUrl)){
                
                //If url has same hostname and we have not visited that url before then add ot to result and visit its neighbors
                if(url.contains(hostName) && !result.contains(url)){
                    
                    queue.add(url);
                    result.add(url);
                }
                
            }
        }
        
        return new ArrayList<String>(result);
        
        
    }
    
    public String getHostName(String startUrl){
        
        String s[] = startUrl.split("/");
        
        //Hostname is at 2nd index
        return s[2];
    }
    
    
}
