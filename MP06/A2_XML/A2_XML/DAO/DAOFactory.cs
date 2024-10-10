namespace A2;
public class DAOFactory
    {
        public IXMLManager CreateXMLDao()
        {
            return new XmlManagerImplementation();
        }

    }
