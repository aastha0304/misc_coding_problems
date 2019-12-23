## Getting Python Kernels

Python packages install

```
conda install --download-only virtualenv
```
gets stored in $ANACONDAHOME/pkgs path
```
sudo `which Conda` install <local path>.tar.bz
```

```
pip uninstall virtualenv
conda install virtualenv
virtualenv -p `which python` dev_env
source dev_env/bin/activate
pip install ipykernel-4.10.0-py2-none-any.whl --no-index --find-links `pwd`
```

Create 2 venv prod and dev

both venv install from anaconda2

Install pyearth in dev first

Check it is accessible from jupyter

```
pip uninstall virtualenv
conda install virtualenv
virtualenv -p `which python` dev_env
source dev_env/bin/activate
pip install ipykernel
idev_env
deactivate
git clone git://github.com/scikit-learn-contrib/py-earth.git
cd py-earth
source dev_env/bin/activate
python setup.py install
deactivate
```

Use this in kernel (dev_env) in jupyter

To delete a jupyter kernel…
```
jupyter kernelspec uninstall unwanted-kernel
```
